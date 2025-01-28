import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class ApiTest {

    @BeforeAll
    public static void setup () {
        RestAssured.baseURI = EnvironmentConfig.get("BASE_URI");
    }

    @Test
    public void testGetEndpoint() {
        given()
            .log().all()
        .when()
            .get("/posts/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void testPostEndpoint() {

        String requestBody = """
            {
                "title": "Test Post 1",
                "body": "This is a body",
                "userId": 1
            }    
            """;

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .log().all()
        .when()
            .post("/posts")
        .then()
            .log().all()
            .statusCode(201)
            .body("title", equalTo("Test Post 1"))
            .body("body", equalTo("This is a body"))
            .body("userId", equalTo(1));
    }
}