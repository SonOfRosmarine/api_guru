package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static specs.Specs.request;

public class ClentEmailTest {

    @Test
    @DisplayName("Проверка наличия пользователя по email")
    void testGetEmail() {
        given()
                .spec(request)
                .when()
                .queryParam("page",2)
                .then()
                .spec(Specs.responseGetEmail)
                .body("data.email", hasItem("michael.lawson@reqres.in"));
    }
}
