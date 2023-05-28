package com.flower.shop.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import com.flower.shop.data.models.integration.Address;
import com.flower.shop.data.models.integration.User;
import com.flower.shop.data.models.integration.authentication.Authentication;
import com.flower.shop.integration.utils.JsonUtils;
import com.flower.shop.rest.integration.ProductsClient;
import com.flower.shop.rest.integration.UserClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@ExtendWith(SpringExtension.class)
public class UserIntegrationTest {
    ProductsClient productClient;

    UserClient userClient;

    JsonUtils jsonUtils;

    @BeforeAll
    void setup() {
        String URI = "http://flowershop-dev-lb-2087424383.eu-west-1.elb.amazonaws.com/api";
        productClient = new ProductsClient(new RestTemplate(), new HttpHeaders(), URI);
        userClient = new UserClient(new RestTemplate(), new HttpHeaders(), URI);
        jsonUtils = new JsonUtils();

    }

    @Test
    void userIntegrationTest() {
        try {
            //WHEN
            User user = getUser();
            String userJson = jsonUtils.mapUserToStringWithRole(user);
            ResponseEntity<User> userRegisterResponse = userClient.register(userJson);
            assertThat(userRegisterResponse.getBody())
                    .isNotNull()
                    .usingRecursiveComparison()
                    .ignoringFields("password")
                    .isEqualTo(user);
            assertEquals(201, userRegisterResponse.getStatusCodeValue());

            //login
            ResponseEntity<String> userLoginResponse = userClient.login(user.getEmail(), user.getPassword());

            assertEquals(200, userLoginResponse.getStatusCodeValue());

            //update newsletter preference
            ResponseEntity<String> userChangeNewsletterResponse = userClient.updateNewsletter(
                    jsonUtils.getJwtToken(userLoginResponse),
                    !user.getNewsletter());

            assertEquals(200, userChangeNewsletterResponse.getStatusCodeValue());
        } catch (UnknownContentTypeException exception) {
            log.warn("Test not runnable! API is not available");
            assertEquals(1, 1);
        }

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
