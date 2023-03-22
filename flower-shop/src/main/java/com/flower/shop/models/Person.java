package com.flower.shop.models;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "persons")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String emailAddress;

    @Column
    private String password;

}
