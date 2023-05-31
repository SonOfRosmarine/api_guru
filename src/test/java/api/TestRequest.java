package api;

import Specs.Specs;
import io.qameta.allure.restassured.AllureRestAssured;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestRequest {


    @Test
    @DisplayName("Проверка наличия пользователя по email")
    void testGetEmail() {
        Specs.request
                .when()
                .get("users?page=2")
                .then()
                .spec(Specs.responseGetEmail);
    }

    @Test
    @DisplayName("Проверка пользователь авторезован")
    void successLoginTest() {

        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        Specs.request
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

        Specs.request
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(Specs.response400);
    }

    @Test
    @DisplayName("Проверка изменения данных пользователя ")
    void changeJobTest() {

        User user = new User();
        user.setName("morpheus");
        user.setJob("Singer");

        Specs.request
                .body(user)
                .when()
                .put("/users/2")
                .then()
                .spec(Specs.responseAll)
                .extract().as(User.class);
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    void successRegistryTest() {

        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        Specs.request
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(Specs.responseRegistryCorrect);
    }

    @Test
    @DisplayName("Проверка не валидные данные для авторизации")
    void unSuccessRegistryTest() {

        User user = new User();
        user.setEmail("sydney@fife");

        Specs.request
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(Specs.responseRegistry400);
    }
}




