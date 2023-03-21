package com.flower.shop.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String country;

    @OneToOne(mappedBy = "deliveryAddress")
    private Order order;

}
