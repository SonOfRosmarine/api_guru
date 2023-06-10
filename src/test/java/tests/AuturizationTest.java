package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;

public class AuturizationTest {

    @Test
    @DisplayName("Проверка пользователь авторезован")
    void successLoginTest() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(Specs.responseLoginCorrect);
    }

    @Test
    @DisplayName("Проверка не валидные данные для авторизации")
    void unSuccessLoginTest() {
        User user = new User();
        user.setEmail("test.holt@reqres.in");
        user.setPassword("pass");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(Specs.response400);
    }
}
