package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;

public class RegistrationTest {

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    void successRegistryTest() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(Specs.responseRegistryCorrect);
    }

    @Test
    @DisplayName("Проверка не валидные данные для регистрации")
    void unSuccessRegistryTest() {
        User user = new User();
        user.setEmail("sydney@fife");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(Specs.responseRegistry400);
    }
}
