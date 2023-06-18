package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static io.restassured.RestAssured.given;
import static specs.Specs.request;


public class ChengDataUserTest {
    @Test
    @DisplayName("Проверка изменения данных пользователя ")
    void changeJobTest() {
        User user = new User();
        user.setName("morpheus");
        user.setJob("Singer");
        given()
                .spec(request)
                .body(user)
                .when()
                .put("/api/users/2")
                .then()
                .spec(Specs.responseChangeData);
    }
}




