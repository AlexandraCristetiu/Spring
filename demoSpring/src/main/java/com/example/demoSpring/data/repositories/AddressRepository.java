package com.example.demoSpring.data.repositories;

import com.example.demoSpring.data.DAO.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends CrudRepository <Address, Integer> {
}

