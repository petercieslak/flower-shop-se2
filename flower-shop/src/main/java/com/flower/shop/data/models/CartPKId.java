package com.flower.shop.data.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@Setter
public class CartPKId implements Serializable {

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
    @Column(name = "client_id", length = 16)
    private UUID clientId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    @Column(name = "product_id", length = 16)
    private UUID productId;
}
