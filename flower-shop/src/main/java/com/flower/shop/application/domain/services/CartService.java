package com.flower.shop.application.domain.services;

import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.CartPKId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CartService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartDAO cartDAO;

    public List<Cart> findClientCart(String mail) {
        UUID cart_id = clientService.getClientIdByMail(mail);
        return cartDAO.findUserCart(cart_id);
    }
    public ResponseEntity<Void> insertCartProducts(String mail, String product_name, Integer quantity){
        UUID cart_id = clientService.getClientIdByMail(mail);
        UUID product_id = productService.getProductIdByName(product_name);
        if(cartDAO.checkIfExists(cart_id, product_id) == 0)
        {
            Cart cart = new Cart();
            CartPKId cartPKId = new CartPKId();
            cartPKId.setProductId(product_id);
            cartPKId.setClientId(cart_id);
            cart.setCartPKId(cartPKId);
            cart.setClient(clientService.getClientByEmail(mail));
            cart.setProduct(productService.getProductByName(product_name));
            cart.setQuantity(1);
            System.out.println(cart);
            cartDAO.save(cart);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FOUND).build();
//            int current_quntatity = cartDAO.checkQuantity(cart_id, product_id);
//            if (current_quntatity + quantity > 0)
//            {
//                return cartDAO.changeQuantityInCart(cart_id, product_id, current_quntatity + quantity);
//            }
//            else {
//                return cartDAO.deleteProductFromCart(cart_id, product_id);
//            }
        }
    }

    public ResponseEntity<Void> changeCartProducts(String mail, String product_name, Integer quantity){
        UUID cart_id = clientService.getClientIdByMail(mail);
        UUID product_id = productService.getProductIdByName(product_name);
        if(cartDAO.checkIfExists(cart_id, product_id) == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            int current_quntatity = cartDAO.checkQuantity(cart_id, product_id);
            if (current_quntatity + quantity > 0)
            {
                cartDAO.changeQuantityInCart(cart_id, product_id, current_quntatity + quantity);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else {
                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).build();
            }
        }
    }

    public ResponseEntity<Void> deleteCartProducts(String mail, String product_name){
        UUID cart_id = clientService.getClientIdByMail(mail);
        UUID product_id = productService.getProductIdByName(product_name);
        if(cartDAO.checkIfExists(cart_id, product_id) == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            cartDAO.deleteProductFromCart(cart_id, product_id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        }
    }
    public int checkIfExists(String mail, String product_name){
        UUID cart_id = clientService.getClientIdByMail(mail);
        UUID product_id = productService.getProductIdByName(product_name);
        return cartDAO.checkIfExists(cart_id, product_id);
    }
}
