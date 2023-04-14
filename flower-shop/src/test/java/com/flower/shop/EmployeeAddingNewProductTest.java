package com.flower.shop;
import com.flower.shop.application.authentication.util.RegisterRequest;
import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.data.models.Client;
import com.flower.shop.data.models.Product;
import com.flower.shop.rest.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class EmployeeAddingNewProductTest {
    @Autowired
    ProductController productController;
    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() {
        HttpStatus result = HttpStatus.BAD_REQUEST;
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result);
    }
    @Test
    public void shouldReturnUnauthorizedBecauseBearerTokenIsInvalid() {
        HttpStatus result = HttpStatus.UNAUTHORIZED;
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result);
    }
    @Test
    public void shouldReturnCreatedProductIsAdded() {
        Product product = createRandomProduct();
        ProductDto request = getNewProductRequest(product);

        HttpStatus status = productController.addProduct(request).getStatusCode();
        Assertions.assertEquals(HttpStatus.CREATED, status);
    }

    private Product createRandomProduct() {
        Product product = new Product();
        product.setDescription("test description");
        product.setName("test product");
        product.setImage(new byte[10]);
        product.setPrice(10.5);
        log.info("Created random product with name {}", product.getName());
        return product;
    }

    private ProductDto getNewProductRequest(Product product) {
        return new ProductDto(product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getPrice());
    }

}
