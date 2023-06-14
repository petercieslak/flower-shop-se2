package com.flower.shop.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flower.shop.data.models.keys.OrderProductsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProducts {

    @EmbeddedId
    OrderProductsId orderProductsId;

    @MapsId("orders")
    @ManyToOne
    @JoinColumn(name = "order_id", columnDefinition = "binary(16)")
    @JsonIgnore
    private Order orders;

    @MapsId("product")
    @ManyToOne
    @JoinColumn(name = "product_id", columnDefinition = "binary(16)")
    @JsonIgnore
    private Product product;

    @Column
    private Integer quantity;
}
