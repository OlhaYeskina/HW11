import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestTest {
    public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
    public static Map<String, Object> jsonAsMap = new HashMap<>();

    @BeforeTest
    public void prepareForNewPostsTest() {
        jsonAsMap.put("userId", 1);
        jsonAsMap.put("title", "New title");
        jsonAsMap.put("body", "New body");
    }

    @Test
    public void postNewPostsTest() {
        given()
                .header("Content-Type", "application/json")
                .body(jsonAsMap)
                .when()
                .post(BASE_URI + "/posts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("userId", equalTo(jsonAsMap.get("userId")))
                .body("title", equalTo(jsonAsMap.get("title")))
                .body("body", equalTo(jsonAsMap.get("body"))).log().all();
    }
}

