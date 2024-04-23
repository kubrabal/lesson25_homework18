import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void postUser() {

        String url = "https://demoqa.com/Account/v1/User";
        String contentType = ContentType.JSON.toString();

        String requestBody = """
                {
                    "userName": "Kübra BAL7",
                    "password": "Kubra1234!!"
                }
            """;

        Map<String, Object> header = new HashMap<>();
        header.put("Content-Type", contentType);

        Response response = given()
                .contentType(contentType)
                .headers(header)
                .body(requestBody)
                .when().log().all()
                .post(url);

        System.out.println(response.getBody().asString());

        assertThat(response.contentType()).isEqualTo("application/json; charset=utf-8");
        assertThat(response.jsonPath().getString("username")).isEqualTo("Kübra BAL7");
        String userID = response.jsonPath().getString("userID");
        assertThat(userID).isNotNull();

        System.out.println("Created user ID: " + userID);
        assertThat(response.statusCode()).isEqualTo(201);

    }

}