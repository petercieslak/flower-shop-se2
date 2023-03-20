package com.flower.shop.models;

import javax.persistence.*;

@Entity
@Table
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int cena;

    @Column
    private String produkt;
}
