package com.example.demoSpring.services;

import com.example.demoSpring.data.DAO.Address;
import com.example.demoSpring.data.DAO.AuthentificationInfo;
import com.example.demoSpring.data.DAO.User;
import com.example.demoSpring.data.DTO.UserDTO;
import com.example.demoSpring.data.repositories.AddressRepository;
import com.example.demoSpring.data.repositories.AuthentificationRepository;
import com.example.demoSpring.data.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("iUserService")
@AllArgsConstructor


   public class UserService implements IUserService{
       private UserRepository userRepository;
       private AddressRepository addressRepository;
       private JavaMailSender javaMailSender;
       private AuthentificationRepository authentificationInfoRepository;



    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<UserDTO>();
        Iterable<User> all = userRepository.findAll();

        all.forEach(u -> users.add(new UserDTO(u)));
        return users;
    }

    @Override
    public void sendMail(Integer userId, String subject, String content) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(u.getEmail());
            mail.setFrom("testEmailSDA@gmail.com");
            mail.setSubject(subject);
            mail.setText(content);

            javaMailSender.send(mail);
        } );
    }

    @Override
    public void modifyUser(Integer id, Optional<String> name, Optional<String> email) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            name.ifPresent(n -> u.setName(n));
            email.ifPresent( e -> u.setEmail(e));

            userRepository.save(u);
        });
    }

    @Override
    public void insertUser(String name, String mail, String password) {
        User u = User.builder().name(name).email(mail).build();


        userRepository.save(u);

        AuthentificationInfo ai = AuthentificationInfo.builder().userName(name).password(password).build();
        authentificationInfoRepository.save(ai);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
//Asa se face conexiunea intre doua tabele, in sensul
// de a modifica datele in doua tabele adresa utilizatorului
// dupa user ID check changeAddress and get UserAddress
    @Override
    public void changeAddress(Integer userId, Optional<String> city, Optional<String> country, Optional<String> street) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(u -> {
             Address address = getUserAddress(u);
             city.ifPresent(c -> address.setCity(c));
             country.ifPresent(c -> address.setCountry(c));
             street.ifPresent(c -> address.setStreet(c));

             Address updatedAddress = addressRepository.save(address);
             u.setAddress(updatedAddress);

             userRepository.save(u);
        });
    }

    @Override
    public String getUserName(Integer userId) {

        Optional<User> user = userRepository.findById(userId);

        return user.get().getName();
    }


    @Override
    public String getServiceTitle(Integer id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        // in programarea functionala in lambda trebuie sa fie valori finale, care nu se vor modifica pe parcurs
        //daca avem nevoie de variabile care is schimba valorile se va merge pe if clasic
        if(user.isPresent()){
            User u = user.get();
            RestTemplate template = new RestTemplate();
            String url = "https://jsonplaceholder.typicode.com/todos/" + u.getId();
            String response = template.getForObject(url, String.class);

            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root
                        = mapper.readTree(response);
                return root.path("title").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new NotFoundException();
    }

    private Address getUserAddress(User u){
        Address address = u.getAddress();
        if(address == null){
            address = new Address();
            address.setUser(u);
        }
        return address;
    }



}

