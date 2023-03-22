package com.flower.shop.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private Boolean hasNewsletterOn;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> order;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Complaint> complaints;

    public void createAccount(Boolean hasNewsletterOn, Person person) {
        this.hasNewsletterOn=hasNewsletterOn;
        this.person=person;
    }
    public void createOrder(){}
    public void signUpForNewsletter(){ this.hasNewsletterOn=true; }
    public void fileComplaint(String topic, String description){}
}
