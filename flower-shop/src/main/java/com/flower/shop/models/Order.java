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

    @Column(columnDefinition = "BINARY(16) DEFAULT (UUID_TO_BIN(UUID()))")
    private UUID clientId;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address deliveryAddress;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public UUID getClientId(){
        return clientId;
    }

    public void setClientId(UUID clientId){
        this.clientId = clientId;
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
