package com.flower.shop.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }


    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public Address getDeliveryAddress(){
        return deliveryAddress;
    }
    public void setDeliveryAddress(Address deliveryAddress){
        this.deliveryAddress = deliveryAddress;
    }

}
