
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequestTest {
    public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
    public static Map<String, Object> jsonAsMap = new HashMap<>();

    Configuration conf = Configuration.builder().jsonProvider( new JacksonJsonProvider()).build();

    @BeforeTest
    public void prepareForNewPostsTest() {
        jsonAsMap.put("id", 1);
        jsonAsMap.put("userId", 1);
        jsonAsMap.put("title", "New title");
        jsonAsMap.put("body", "New body");
    }

    @Test
    public void putUpdatedFirstPostTest() {
        given()
                .header("Content-Type", "application/json")
                .body(jsonAsMap)
                .when()
                .put(BASE_URI + "/posts/1")
                .then()
                .assertThat()
                .body("id", equalTo(jsonAsMap.get("id")))
                .body("userId", equalTo(jsonAsMap.get("userId")))
                .body("title", equalTo(jsonAsMap.get("title")))
                .body("body", equalTo(jsonAsMap.get("body"))).log().all();
    }


    @Test
    public void putUpdatedSecondPostTest(){
        JsonFileReader requestBody = new JsonFileReader();

        given()
                .header("Content-Type", "application/json")
                .body(requestBody.bodyJson())
                .when()
                .put(BASE_URI + "/posts/2")
                .then()
                .assertThat()
                .body("id", equalTo(JsonPath.using(conf).parse(requestBody.bodyJson()).read("$.id")))
                .body("userId", equalTo(JsonPath.using(conf).parse(requestBody.bodyJson()).read("$.userId")))
                .body("title", equalTo(JsonPath.using(conf).parse(requestBody.bodyJson()).read("$.title")))
                .body("body", equalTo(JsonPath.using(conf).parse(requestBody.bodyJson()).read("$.body"))).log().all();
    }
}
