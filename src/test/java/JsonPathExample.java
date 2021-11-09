import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;


public class JsonPathExample {
    public static void main(String[] args) {
        String jsonExample = "";

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonExample);
//указать ноду по вложеной иерархии
        String ex1 = JsonPath.read(document, "$.");
    }
}
