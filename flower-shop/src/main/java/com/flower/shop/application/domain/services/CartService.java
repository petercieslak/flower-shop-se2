package com.flower.shop.application.domain.services;

import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.CartPKId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int insertIntoCartProducts(String mail, String product_name, Integer quantity){
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
            cart.setQuantity(quantity);
            System.out.println(cart);
            cartDAO.save(cart);
            return 1;
        }
        else {
            if (quantity > 0)
            {
                return cartDAO.changeQuantityInCart(cart_id, product_id, quantity);
            }
            else {
                return cartDAO.deleteProductFromCart(cart_id, product_id);
            }
        }
    }
}
