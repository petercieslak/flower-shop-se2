package com.flower.shop.data.models;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @Column(name = "id", length = 16)
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Client client;

    @ManyToMany
    private List<Product> products;
}
