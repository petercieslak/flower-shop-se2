package com.flower.shop.application.domain.services;

import com.flower.shop.application.dto.CartProductsDto;
import com.flower.shop.data.dao.CartProductsDAO;
import com.flower.shop.data.models.Cart;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CartProductsService {

    @Autowired
    private CartProductsDAO cartProductsDAO;

    public List<Cart> findAll() {
        return cartProductsDAO.findAll();
    }
//    EntityManager em = new EntityManager();
//    public List<CartProductsDto> findCartProductsByClientId(UUID id){
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<CartProductsDto> cq = cb.createQuery(CartProductsDto.class);
//
//        Root<CartProductsDto> cartProductsRoot = cq.from(CartProductsDto.class);
//        Predicate idPredicate = cb.equal(cartProductsRoot.get("order_id"), id);
//        cq.where(idPredicate);
//
//        TypedQuery<CartProductsDto> query = em.createQuery(cq);
//
//        return query.getResultList();
//    }
}
