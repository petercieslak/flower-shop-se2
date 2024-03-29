package com.flower.shop.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", length=16, unique = true, nullable = false)
    private UUID id;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_man_id", nullable = false)
    private DeliveryMan deliveryMan;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderProducts> products;
    
}
