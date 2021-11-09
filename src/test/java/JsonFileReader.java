
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class JsonFileReader {



    public String bodyJson() {
        String json = "";
        try
        {

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader("BodyForPutSecondPostTest.json"));//path to the JSON file.

            json = data.toJSONString();
            System.out.println(json);
//            Configuration conf = Configuration.builder().jsonProvider( new JacksonJsonProvider()).build();
//
//            Integer userId = JsonPath.using(conf).parse(json).read("$.userId");
//
//            System.out.println(userId);
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return json;
    }


}

