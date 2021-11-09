import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class DeleteRequestTest {
    public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
    public static Map<String, Object> jsonAsMap = new HashMap<>();

    @BeforeTest
    public void prepareForNewPostsTest() {
        jsonAsMap.put("id", 1);
        jsonAsMap.put("userId", 1);
        jsonAsMap.put("title", "New title");
        jsonAsMap.put("body", "New body");
    }

    @Test
    public void deleteFirstPostTest() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .delete(BASE_URI + "/posts/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("isEmpty()", Matchers.is(true)).log().all();
    }
}
