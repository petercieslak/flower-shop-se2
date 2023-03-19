package com.flower.shop.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test")
public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(name = "start_date")
    private LocalDateTime startDateTime;


}
