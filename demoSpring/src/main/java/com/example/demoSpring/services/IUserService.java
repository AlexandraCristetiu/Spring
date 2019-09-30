package com.example.demoSpring.services;

import com.example.demoSpring.data.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUsers();
    void sendMail(Integer userId, String subject, String content);
    void modifyUser(Integer id, Optional<String> name, Optional<String> email);
    void insertUser(String name, String mail, String password);
    void delete(Integer id);
    void changeAddress(Integer userId,Optional<String> city, Optional<String> country,Optional<String> street );
    String getUserName(Integer userId);

    // prin getServiceTitle se face conectarea la un serviciu extern
    String getServiceTitle(Integer id) throws NotFoundException;
}
