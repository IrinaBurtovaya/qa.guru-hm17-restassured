import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specs {

        public static RequestSpecification request = with()
            .baseUri("https://reqres.in/api")
            .log().uri()
            .log().body()
            .contentType(JSON);

        public static ResponseSpecification response = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
}
