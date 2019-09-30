package com.example.demoSpring.services;

import com.example.demoSpring.data.DAO.AuthentificationInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private AuthentificationInfo authentificationInfo;

    public CustomUserDetails(AuthentificationInfo authentificationInfo){
        this.authentificationInfo = authentificationInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return authentificationInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return authentificationInfo.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
