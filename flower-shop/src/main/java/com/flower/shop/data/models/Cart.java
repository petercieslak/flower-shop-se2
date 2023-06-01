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

//    @Id
//    @Column(name = "id", length = 16)
//    private UUID id;

    @EmbeddedId
    private CartPKId cartPKId;
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private Client client;
//
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id", referencedColumnName = "productId")
//    private Product product;

    @MapsId("productId")
    @ManyToOne
//    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("clientId")
    @ManyToOne
    private Client client;

    @Column(name = "quantity")
    private int quantity;
}
