package com.fatmadelenn.car;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    public static final String API_CAR = "/api/car/";

    @Test
    public void getType_whenNameIsDeneme_receiveNotFound() {
        ResponseEntity<String> responseEntity = getType("deneme", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getType_whenNameIsSedan_receiveOkAndReceiveCorrectMessage() {
        ResponseEntity<String> responseEntity = getType("sedan", String.class);
        assertThat(responseEntity.getBody()).isEqualTo("Sedan Car has produced.");
    }

    @Test
    public void getType_whenNameIsCabrio_receiveOkAndReceiveCorrectMessage() {
        ResponseEntity<String> responseEntity = getType("cabrio", String.class);
        assertThat(responseEntity.getBody()).isEqualTo("Cabrio Car has produced.");
    }

    @Test
    public void getType_whenNameIsHatchback_receiveOkAndReceiveCorrectMessage() {
        ResponseEntity<String> responseEntity = getType("Hatchback", String.class);
        assertThat(responseEntity.getBody()).isEqualTo("Hatchback Car has produced.");
    }

    public <T> ResponseEntity<T> getType(String type, Class<T> responseType) {
        return testRestTemplate.getForEntity(API_CAR.concat(type), responseType);
    }
}
