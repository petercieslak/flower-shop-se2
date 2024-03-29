package com.flower.shop.data.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(length=16)
    private UUID id;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String country;


}
