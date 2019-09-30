package com.example.demoSpring.data.repositories;

import com.example.demoSpring.data.DAO.AuthentificationInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthentificationRepository extends CrudRepository <AuthentificationInfo, Integer>{

    Optional<AuthentificationInfo>findByUserName(String userName);

}
