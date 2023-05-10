package com.flower.shop;
import com.flower.shop.application.authentication.AuthenticationService;
import com.flower.shop.application.authentication.util.RegisterRequest;
import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.EmployeeDAO;
import com.flower.shop.data.models.Client;
import com.flower.shop.data.models.Employee;
import com.flower.shop.data.models.Product;
import com.flower.shop.rest.ProductController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.*;
import java.util.Date;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class EmployeeAddingNewProductTest {
    @Autowired
    ProductController productController;

    @Autowired
    AuthenticationService authService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private EmployeeDAO employeeRepository;

    @Autowired
    private ClientDAO clientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SECRET_KEY = "4528482B4D6251655468576D597133743677397A24432646294A404E63526655";


    @BeforeAll
    private void setTestDatabase(){
        RegisterRequest empRequest = new RegisterRequest("admin", "admin", "admin@admin.com",
                "admin", false);

        Employee emp = authService.initializeEmployee(empRequest);
        employeeRepository.save(emp);

        RegisterRequest userRequest = new RegisterRequest("user", "user", "user@user.com",
                "user", false);

        Client user = authService.initializeClient(userRequest);
        clientRepository.save(user);
    }

    @AfterAll
    private void clearDatabase() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "cart", "client", "employee", "persons", "product");
    }

    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() throws URISyntaxException{
        String baseUrl = "http://localhost:"+randomServerPort+"/api/products";
        URI uri = new URI(baseUrl);

        Product product = createInvalidRandomProduct();
        ProductDto productdto = getNewProductRequest(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token_admin);


        HttpEntity<ProductDto> request = new HttpEntity<>(productdto, headers);

        ResponseEntity<Void> result = this.restTemplate.postForEntity(uri, request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    @Test
    public void shouldReturnUnauthorizedBecauseThereIsNoToken() throws URISyntaxException{
        String baseUrl = "http://localhost:"+randomServerPort+"/api/products";
        URI uri = new URI(baseUrl);

        Product product = createInvalidRandomProduct();
        ProductDto productdto = getNewProductRequest(product);

        HttpHeaders headers = new HttpHeaders();


        HttpEntity<ProductDto> request = new HttpEntity<>(productdto, headers);

        ResponseEntity<Void> result = this.restTemplate.postForEntity(uri, request, Void.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void shouldReturnForbiddenBecauseUserIsNotEmployee() throws URISyntaxException{
        String baseUrl = "http://localhost:"+randomServerPort+"/api/products";
        URI uri = new URI(baseUrl);

        Product product = createRandomProduct();
        ProductDto productdto = getNewProductRequest(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token_user);


        HttpEntity<ProductDto> request = new HttpEntity<>(productdto, headers);

        ResponseEntity<Void> result = this.restTemplate.postForEntity(uri, request, Void.class);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
    }
    @Test
    public void shouldReturnCreatedProductIsAdded() throws URISyntaxException {
        String baseUrl = "http://localhost:"+randomServerPort+"/api/products";
        URI uri = new URI(baseUrl);

        Product product = createRandomProduct();
        ProductDto productdto = getNewProductRequest(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token_admin);


        HttpEntity<ProductDto> request = new HttpEntity<>(productdto, headers);

        ResponseEntity<Void> result = this.restTemplate.postForEntity(uri, request, Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());

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

    private Product createInvalidRandomProduct() {
        Product product = new Product();
        product.setDescription("test description");
        product.setName("test product");
        product.setImage(new byte[10]);
        product.setPrice(-10);
        log.info("Created invalid random product with name {}", product.getName());
        return product;
    }

    private ProductDto getNewProductRequest(Product product) {
        return new ProductDto(product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getPrice());
    }

    String token_admin = Jwts.builder()
            .setSubject("admin@admin.com")
            .claim("authorities", "ADMIN")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    String token_user = Jwts.builder()
            .setSubject("user@user.com")
            .claim("authorities", "USER")
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + 86400000))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
