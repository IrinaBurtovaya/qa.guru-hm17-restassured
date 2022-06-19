//import models.UserData;

import lombok.UserData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

// В первые 2 теста добавлен принцип POJO объектов
// Также в тесты добавлены спецификации
public class ReqresinTests {
    @Test
        // Здесь проверяю в конкретном объекте списка
    void checkUsersEmail() {
        List<UserData> users = given()
                .spec(Specs.request)
                .get("/users?page=2")
                .then()
                .spec(Specs.response)
                .body("total", is(12))
                .extract().jsonPath().getList("data", UserData.class);
        assertEquals("byron.fields@reqres.in", users.get(3).getEmail());
    }

    @Test
        // Здесь проверяю во всем списке
    void checkEmailUsingGroovy() {
        given()
                .spec(Specs.request)
                .get("/users?page=2")
                .then()
                .spec(Specs.response)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("byron.fields@reqres.in"))
                .body("data.findAll{it.first_name}.first_name.flatten()", hasItem("Byron"));
    }

    @Test
    void checkSingleResource() {
        UserData data = given()
                .spec(Specs.request)
                .get("/unknown/2")
                .then()
                .spec(Specs.response)
                .extract().jsonPath().getObject("data", UserData.class);
        assertEquals("fuchsia rose", data.getName());
    }

    @Test
    void createUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"598\", \"createdAt\": \"2022-06-07T11:53:47.580Z\" }";

        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .body("name", is("morpheus"));
    }

    @Test
    void deleteUserStatusCode204() {
        given()
                .spec(Specs.request)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void checkUnsuccessfulLogin() {
        String body = "{ \"email\": \"peter@klaven\" }";
        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
