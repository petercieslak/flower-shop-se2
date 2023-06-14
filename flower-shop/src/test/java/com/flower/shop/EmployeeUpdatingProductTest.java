package com.flower.shop;

import com.flower.shop.application.authentication.AuthenticationService;
import com.flower.shop.application.authentication.util.RegisterRequest;
import com.flower.shop.application.domain.services.ProductService;
import com.flower.shop.application.dto.ProductDto;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.dao.EmployeeDAO;
import com.flower.shop.data.dao.ProductDAO;
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
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class EmployeeUpdatingProductTest {

    @Autowired
    ProductController productController;

    @Autowired
    AuthenticationService authService;

    @Autowired
    ProductService productsService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private EmployeeDAO employeeRepository;

    @Autowired
    private ProductDAO productRepository;

    @Autowired
    private ClientDAO clientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SECRET_KEY = "4528482B4D6251655468576D597133743677397A24432646294A404E63526655";


    @BeforeEach
    private void setTestDatabase(){
        RegisterRequest empRequest = new RegisterRequest("admin", "admin", "admin@admin.com",
                "admin", false);

        UUID uuid = UUID.randomUUID();
        byte[] emptyArray = new byte[0];
        ProductDto prodRequest = new ProductDto(uuid, "test", "test", emptyArray, 10, "gift", 1);
        productsService.createProduct(prodRequest);

        Employee emp = authService.initializeEmployee(empRequest);
        employeeRepository.save(emp);

        RegisterRequest userRequest = new RegisterRequest("user", "user", "user@user.com",
                "user", false);

        Client user = authService.initializeClient(userRequest);
        clientRepository.save(user);
    }

    @AfterEach
    private void clearDatabase(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "cart", "client", "employee", "persons", "product");
    }
    @Test
    public void shouldReturnUnauthorizedBecauseThereIsNoToken() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();

        Product p = productRepository.findByName("test");
        UUID uuid = UUID.randomUUID();
        byte[] emptyArray = new byte[0];
        ProductDto productModified = new ProductDto(uuid, "t", "t", emptyArray, 10, "potted", 1);
        HttpEntity<ProductDto> request = new HttpEntity<>(productModified, headers);

        String baseUrl = "http://localhost:"+randomServerPort+"/api/products/"+p.getProductId();
        URI uri = new URI(baseUrl);

        ResponseEntity<Void> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }
    @Test
    public void shouldReturnNotFoundBecauseProductNotExisting() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token_admin);
        UUID uuid = UUID.randomUUID();
        byte[] emptyArray = new byte[0];
        ProductDto productModified = new ProductDto(uuid, "t", "t", emptyArray, 10, "potted", 1);
        HttpEntity<ProductDto> request = new HttpEntity<>(productModified, headers);

        String baseUrl = "http://localhost:"+randomServerPort+"/api/products/"+uuid;
        URI uri = new URI(baseUrl);

        ResponseEntity<Void> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token_admin);
        Product p = productRepository.findByName("test");
        UUID uuid = UUID.randomUUID();
        byte[] emptyArray = new byte[0];
        ProductDto productModified = new ProductDto(uuid, "t", "t", emptyArray, -10, "potted", 1);
        HttpEntity<ProductDto> request = new HttpEntity<>(productModified, headers);

        String baseUrl = "http://localhost:"+randomServerPort+"/api/products/"+p.getProductId();
        URI uri = new URI(baseUrl);

        ResponseEntity<Void> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    @Test
    public void shouldReturnCreatedProductIsUpdating() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + token_admin);
        Product p = productRepository.findByName("test");
        UUID uuid = UUID.randomUUID();
        byte[] emptyArray = new byte[0];
        ProductDto productModified = new ProductDto(uuid, "t", "t", emptyArray, 10, "potted", 1);
        HttpEntity<ProductDto> request = new HttpEntity<>(productModified, headers);

        String baseUrl = "http://localhost:"+randomServerPort+"/api/products/"+p.getProductId();
        URI uri = new URI(baseUrl);

        ResponseEntity<Void> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    String token_admin = Jwts.builder()
            .setSubject("admin@admin.com")
            .claim("authorities", "ADMIN")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
