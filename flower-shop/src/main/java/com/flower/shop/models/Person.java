package com.flower.shop.models;
import lombok.Data;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String emailAddress;
}
