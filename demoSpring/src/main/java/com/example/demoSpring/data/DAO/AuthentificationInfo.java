package com.example.demoSpring.data.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class AuthentificationInfo {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String userName;
    private String password;

    @OneToOne(mappedBy = "authentificationInfo")
    private User user;

    @Builder
    private AuthentificationInfo(String userName, String password){
        this.userName = userName;
        this.password = password;
    }



}
