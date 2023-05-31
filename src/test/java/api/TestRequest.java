package api;

import Specs.Specs;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static Specs.Specs.request;
import static io.restassured.RestAssured.given;


public class TestRequest {


    @Test
    @Tag("CheckApi")
    @DisplayName("Проверка наличия пользователя по email")
    void testGetEmail() {
        given()

                .spec(request)
                .when()
                .get("users?page=2")
                .then()
                .spec(Specs.responseGetEmail);
    }

    @Test
    @Tag("CheckApi")
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
    @Tag("CheckApi")
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

    @Test
    @Tag("CheckApi")
    @DisplayName("Проверка изменения данных пользователя ")
    void changeJobTest() {

        User user = new User();
        user.setName("morpheus");
        user.setJob("Singer");
        given()

                .spec(request)
                .body(user)
                .when()
                .put("/users/2")
                .then()
                .spec(Specs.responseAll)
                .extract().as(User.class);
    }

    @Test
    @Tag("CheckApi")
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
    @Tag("CheckApi")
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




