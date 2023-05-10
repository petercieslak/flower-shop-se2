package com.flower.shop.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @Column(name = "client_id", length = 16)
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Client client;

    @ManyToMany
    private List<Product> products;
}
