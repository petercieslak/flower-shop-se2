package com.flower.shop.data.models;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryMan extends Person{

    @Column
    private String deliveryCity;

}

