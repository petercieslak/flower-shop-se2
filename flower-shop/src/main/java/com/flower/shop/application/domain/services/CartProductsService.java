package com.flower.shop.application.domain.services;

import com.flower.shop.data.dao.CartProductsDAO;
import com.flower.shop.data.models.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CartProductsService {

    @Autowired
    private CartProductsDAO cartProductsDAO;

    public List<String> findAllItems(UUID uuid) {
        return cartProductsDAO.findAllItems(uuid);
    }

    public int deleteProductFromCart(UUID cart_id, String product_id){
        return cartProductsDAO.deleteProductFromCart(cart_id, product_id);
    };
//    @Autowired
//    EntityManager em;
//    public Path<Object> findCartProductsByClientId(UUID id){
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
//
//        Root<Cart> cartProductsRoot = cq.from(Cart.class);
//
//        return cartProductsRoot.get("id");

//        Predicate idPredicate = cb.equal(cartProductsRoot.get("cart_id"), id);
//        cq.where(idPredicate);
//
//        TypedQuery<Cart> query = em.createQuery(cq);

//        return query.getResultList();
}
