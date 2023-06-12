package com.flower.shop.data.models;

import com.flower.shop.application.dto.CartDto;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @EmbeddedId
    private CartPKId cartPKId;

    @MapsId("productId")
    @ManyToOne
    private Product product;

    @MapsId("clientId")
    @ManyToOne
    private Client client;

    @Column(name = "quantity")
    private int quantity;
}
