package com.flower.shop;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flower.shop.application.dto.AddressDto;
import com.flower.shop.data.dao.CartDAO;
import com.flower.shop.data.dao.ClientDAO;
import com.flower.shop.data.models.Cart;
import com.flower.shop.data.models.Client;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.UUID;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Main.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@Slf4j
public class ClientPlacingOrderTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDAO clientRepository;

    @Autowired
    private CartDAO cartRepository;

    @AfterAll
    private void teardown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "orders", "cart", "client", "persons");
    }


    @BeforeAll
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnBadRequestBecauseBodyIsInvalid() throws Exception {
        UUID clientUUID = getRandomClientUUID();
        mockMvc.perform(post("/api/orders/" + clientUUID)
                    .content("{ city: \"wa2r3..zawa\", country: \"Poland\", postalCode: \"ffg/000\", street: \"Some street\"}")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(roles = "LOGOUT")
    public void shouldReturnUnauthorizedBecauseBearerTokenIsInvalid() throws Exception {
        UUID clientUUID = getRandomClientUUID();
        mockMvc.perform(post("/api/orders/" + clientUUID)
                        .content("{ city: \"Warszawa\", country\": \"Poland\", postalCode: \"00-000\", street: \"string\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        HttpStatus result = HttpStatus.UNAUTHORIZED;
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, result);
    }
    @Test
    @WithMockUser
    public void shouldReturnCreatedBecauseNewOrderWasCreated() throws Exception {
        UUID clientUUID = addClient();
        mockMvc.perform(post("/api/orders/" + clientUUID)
                        .content(getAddressJson())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private UUID getRandomClientUUID() {
        return UUID.randomUUID();
    }

    private UUID addClient() {
        Client client = createRandomPerson();
        Cart cart = Cart.builder()
                .client(client)
                .products(new ArrayList<>())
                .build();
        cartRepository.save(cart);
        return client.getId();
    }

    private Client createRandomPerson() {
        String email = RandomString.make(6);
        Client client = new Client();
        client.setEmail(email+"@email.com");
        client.setPassword("password");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setHasNewsletterOn(true);
        log.info("Created random person with email {}", client.getEmail());
        return client;
    }

    private String getAddressJson() throws JsonProcessingException {
        AddressDto address = AddressDto.builder()
                .country("Poland")
                .city("Warszawa")
                .postalCode("00-000")
                .street("Warszawska 4/23")
                .build();
        return objectMapper.writeValueAsString(address);
    }
}
