package com.flower.shop.data.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Client extends Person {
    @Column
    private Boolean hasNewsletterOn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }


}
