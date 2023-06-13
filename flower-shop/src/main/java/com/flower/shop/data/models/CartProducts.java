package com.flower.shop.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flower.shop.data.models.keys.CartProductsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "cart_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProducts {

    @EmbeddedId
    CartProductsId cartProductsId;

    @MapsId("cart")
    @ManyToOne
    @JoinColumn(name = "cart_id", columnDefinition = "binary(16)")
    @JsonIgnore
    private Cart cart;

    @MapsId("product")
    @ManyToOne
    @JsonIgnore
    private Product product;

    @Column
    private Integer quantity;
}
