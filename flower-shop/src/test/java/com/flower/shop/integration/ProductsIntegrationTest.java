package com.flower.shop.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flower.shop.integration.utils.JsonUtils;
import com.flower.shop.rest.integration.BasketClient;
import com.flower.shop.rest.integration.ProductsClient;
import com.flower.shop.rest.integration.UserClient;
import com.flower.shop.data.models.integration.Address;
import com.flower.shop.data.models.integration.Product;
import com.flower.shop.data.models.integration.User;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@ExtendWith(SpringExtension.class)
public class ProductsIntegrationTest {

    ProductsClient productClient;

    UserClient userClient;

    BasketClient basketClient;

    JsonUtils jsonUtils;

    @BeforeAll
    void init() {
        String URI = "https://pw-flowershop.azurewebsites.net/api";
        productClient = new ProductsClient(new RestTemplate(), new HttpHeaders(), URI);
        userClient = new UserClient(new RestTemplate(), new HttpHeaders(), URI);
        basketClient = new BasketClient(new RestTemplate(), new HttpHeaders(), URI);
        jsonUtils = new JsonUtils();
    }

    @Test
    void createNewProductIntegrationTest() {
        //GIVEN
        ResponseEntity<String> loginResponse = userClient.login("karol.nowak@flowershop.com", "admin");
        assertEquals(loginResponse.getStatusCode().value(), 200);

        //WHEN
        Product newProduct = getProduct();
        ResponseEntity<String> productResponse = productClient.createProduct(newProduct,
                jsonUtils.getJwtToken(loginResponse));

        //THEN
        assertEquals(productResponse.getStatusCode().value(), 201);
        assertThat(productClient.getProduct(newProduct.getProductID()) != null &&
                productClient.getProduct(newProduct.getProductID()).getName() == "Rose");
    }

    @Test
    void createOrderTest() {
        //GIVEN
        ResponseEntity<String> loginResponse = userClient.login("karol.nowak@flowershop.com", "admin");
        assertEquals(loginResponse.getStatusCode().value(), 200);

        Product newProduct = getProduct();
        ResponseEntity<String> productResponse = productClient.createProduct(newProduct,
                jsonUtils.getJwtToken(loginResponse));

        assertEquals(productResponse.getStatusCode().value(), 201);
        assertThat(productClient.getProduct(newProduct.getProductID()) != null &&
                productClient.getProduct(newProduct.getProductID()).getName() == "Rose");

        //WHEN
        User user = getUser();
        ResponseEntity<User> registerResponse = userClient.register(user);
        assertEquals(201, registerResponse.getStatusCodeValue());

        loginResponse = userClient.login(user.getEmail(), user.getPassword());
        assertEquals(200, loginResponse.getStatusCodeValue());

        ResponseEntity<String> addProductToBasketResponse = basketClient.addProduct(newProduct.getProductID(),
                jsonUtils.getJwtToken(loginResponse));

        //THEN
        assertEquals(201, addProductToBasketResponse.getStatusCodeValue());
    }

    private Product getProduct() {
        return Product.builder()
                .productID(UUID.randomUUID())
                .name("Rose")
                .description("Beautiful rose")
                .image(RandomString.make())
                .price(9.99d)
                .quantity(1)
                .category("flower")
                .build();
    }

    private User getUser() {
        return User.builder()
                .name("John Doe")
                .email(RandomString.make(5)+"@mail.com")
                .password("password")
                .newsletter(false)
                .address(createAddress())
                .build();
    }

    private Address createAddress() {
        return Address.builder()
                .country("Poland")
                .city("Warsaw")
                .buildingNo("10")
                .houseNo("10")
                .street("Warszawska")
                .postalCode("00-000")
                .build();
    }
}
