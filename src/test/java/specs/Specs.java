package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.allureWithCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.*;


public class Specs {

    public static RequestSpecification request = with()
            .filter(allureWithCustomTemplates())
            .baseUri("https://reqres.in")
            .log().all()
            .contentType(ContentType.JSON);
    public static ResponseSpecification responseGetEmail = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("data.email", hasItem("michael.lawson@reqres.in"))
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification responseChangeData = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("name", is("morpheus"))
            .expectBody("job", is("Singer"))
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification responseLoginCorrect = new ResponseSpecBuilder()
            .expectBody("token", is(notNullValue()))
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectBody("error", is("user not found"))
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseRegistryCorrect = new ResponseSpecBuilder()
            .expectBody("id", is(4))
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification responseRegistry400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectBody("error", is("Missing password"))
            .log(STATUS)
            .log(BODY)
            .build();
}
