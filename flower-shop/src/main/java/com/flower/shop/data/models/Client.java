package com.flower.shop.data.models;

import javax.persistence.*;

@Entity
public class Client extends Person {
    @Column
    private Boolean hasNewsletterOn;



}
