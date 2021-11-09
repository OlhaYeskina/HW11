import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import groovy.transform.ToString;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestTest {

    public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
    public static Map<String, Object> jsonAsMap = new HashMap<>();
    public static Map<String, Object> jsonAsMap1 = new HashMap<>();

    @BeforeTest
    public void prepareForFirstPostsTest() {
        jsonAsMap.put("userId", 1);
        jsonAsMap.put("id", 1);
        jsonAsMap.put("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        jsonAsMap.put("body", "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        jsonAsMap1.put("userId", 1);
        jsonAsMap1.put("id", 2);
        jsonAsMap1.put("title", "qui est esse");
        jsonAsMap1.put("body", "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla");


    }

    @Test
    public void getFirstPostsTest() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get(BASE_URI + "/posts/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("userId", equalTo(jsonAsMap.get("userId")))
                .body("id", equalTo(jsonAsMap.get("id")))
                .body("title", equalTo(jsonAsMap.get("title")))
                .body("body", equalTo(jsonAsMap.get("body"))).log().all();
    }

    @Test
    public void getFromAllPostsSecondOneTest() {
        String response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(BASE_URI + "/posts").asString();


        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(response);
        int userId2 = JsonPath.read(doc, "$.[1].userId");
        System.out.println(userId2);
        int id2 = JsonPath.read(doc, "$.[1].id");
        System.out.println(id2);

        Assert.assertEquals(userId2, jsonAsMap1.get("userId"));
        Assert.assertEquals(id2, jsonAsMap1.get("id"));

    }


    @Test
    public void getAllPostsTest() {
        int numberOfPostExpected = 100;
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(BASE_URI + "/posts");
        List<PostExample> postList = Arrays.asList(response.getBody().as(PostExample[].class));
        int numberOfPostActual = postList.size();
        Assert.assertEquals(numberOfPostExpected,numberOfPostActual);

//PostExample post = given()
//                .header("Content-Type", "application/json")
//                .when()
//                .get(BASE_URI + "/posts")
//                .getBody()
//                .as(PostExample.class);

    }

}
