import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {

    @Test
    void checkUsersTotal() {
        get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void checkSingleResource() {
        get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.name", is("fuchsia rose"));
    }

    @Test
    void createUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"598\", \"createdAt\": \"2022-06-07T11:53:47.580Z\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .body("name", is("morpheus"));
    }

    @Test
    void deleteUserStatusCode204() {
        given()
                .log().uri()
                .log().body()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void checkUnsuccessfulLogin() {
        String body = "{ \"email\": \"peter@klaven\" }";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
