package com.flower.shop.application.domain.services;

import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.ProductDAO;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.CartProducts;
import com.flower.shop.data.models.Client;
import com.flower.shop.data.models.Product;
import com.flower.shop.data.models.keys.CartProductsId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CartProductsService {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ProductDAO productDAO;

    public List<CartProducts> getProducts(UUID uuid) {
        Client client = (Client)clientDAO.findById(uuid).get();
        return cartDAO.findCartByClient(client)
                .get()
                .getProducts();
    }

    public Cart addProduct(UUID userId, UUID productId, Integer quantity) {
        Product product = productDAO.findById(productId).get();
        Client client = (Client)clientDAO.findById(userId).get();
        Cart clientCart = cartDAO.findCartByClient(client).get();
        clientCart = addProductToCart(clientCart, product, quantity);
        cartDAO.save(clientCart);
        return clientCart;
    }

    public Cart removeProduct(UUID userId, UUID productId) {
        Product product = productDAO.findById(productId).get();
        Client client = (Client)clientDAO.findById(userId).get();
        Cart clientCart = cartDAO.findCartByClient(client).get();
        clientCart = removeProductFromCart(clientCart, product);
        cartDAO.save(clientCart);
        return clientCart;
    }

    private Cart removeProductFromCart(Cart clientCart, Product product) {
        List<CartProducts> cartProducts = clientCart.getProducts();
        CartProducts cartProduct = getMatchingProduct(cartProducts, product);
        cartProducts.remove(cartProduct);
        clientCart.setProducts(cartProducts);
        return clientCart;
    }

    private Cart addProductToCart(Cart clientCart, Product product, Integer quantity) {
        List<CartProducts> cartProducts = clientCart.getProducts();
        CartProducts cartProduct = getMatchingProduct(cartProducts, product);
        if(cartProduct.getQuantity() == 0)
            cartProducts = addNewProduct(clientCart, cartProducts, product, quantity);
        else
            cartProducts = editQuantity(cartProducts, cartProduct, quantity);
        clientCart.setProducts(cartProducts);
        return clientCart;
    }

    private List<CartProducts> editQuantity(List<CartProducts> cartProducts, CartProducts cartProduct, Integer quantity) {
        int index = cartProducts.indexOf(cartProduct);
        cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        cartProducts.set(index, cartProduct);
        return cartProducts;
    }

    private List<CartProducts> addNewProduct(Cart clientCart,
                                             List<CartProducts> cartProducts, Product product, Integer quantity) {
        CartProducts cartProduct = new CartProducts();
        cartProduct.setCartProductsId(new CartProductsId(clientCart.getId(), product.getProductId()));
        cartProduct.setCart(clientCart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);
        cartProducts.add(cartProduct);
        return cartProducts;
    }

    private CartProducts getMatchingProduct(List<CartProducts> cartProducts, Product product) {
        CartProducts cartProductToEdit = new CartProducts();
        cartProductToEdit.setQuantity(0);
        for(CartProducts cartProduct : cartProducts) {
            if(cartProduct.getProduct().getProductId() == product.getProductId()) {
                cartProductToEdit = cartProduct;
                break;
            }
        }
        return cartProductToEdit;
    }
}
