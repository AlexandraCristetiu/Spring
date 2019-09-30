package com.example.demoSpring.services;

import com.example.demoSpring.data.DAO.AuthentificationInfo;
import com.example.demoSpring.data.repositories.AuthentificationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    private AuthentificationRepository authentificationRepository;

    public LoginService(AuthentificationRepository authentificationRepository){
        this.authentificationRepository = authentificationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthentificationInfo ai = authentificationRepository.findByUserName(s).orElseThrow(()->new UsernameNotFoundException(s));
        return new CustomUserDetails(ai);
    }
}
