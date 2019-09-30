package com.example.demoSpring.data.DAO;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity

@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @OneToOne
    @JoinColumn(name = "authentification_id")
    private AuthentificationInfo authentificationInfo;

    @Transient
    private long age;

    /*@PostLoad
    public void calculateAge(){
        age = ChronoUnit.YEARS.between(birhDate, LocalDate.now());
    }*/



    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }



}
