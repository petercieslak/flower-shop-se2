package com.flower.shop.data.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "client_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Client client;

    @ManyToMany
    private List<Product> products;
}
