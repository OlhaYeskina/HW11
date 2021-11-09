import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequestTest {
    public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
    public static Map<String, Object> jsonAsMap = new HashMap<>();
    public static Map<String, Object> jsonAsMap1 = new HashMap<>();


    @BeforeTest
    public void prepareForNewPostsTest() {

        jsonAsMap.put("title", "New title");

        jsonAsMap1.put("userId", 1);
        jsonAsMap1.put("id", 1);
        jsonAsMap1.put("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        jsonAsMap1.put("body", "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
    }

    @Test
    public void patchUpdatedTitleFirstPostTest() {
        given()
                .header("Content-Type", "application/json")
                .body(jsonAsMap)
                .when()
                .patch(BASE_URI + "/posts/1")
                .then()
                .assertThat()
                .body("id", equalTo(jsonAsMap1.get("id")))
                .body("userId", equalTo(jsonAsMap1.get("userId")))
                .body("title", equalTo(jsonAsMap.get("title")))
                .body("body", equalTo(jsonAsMap1.get("body"))).log().all();
    }
}
