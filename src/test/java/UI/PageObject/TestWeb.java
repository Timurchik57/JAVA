package UI.PageObject;

import UI.Abstract;
import UI.TestListener;
import UI.TestlistnerApi;
import com.jayway.jsonpath.DocumentContext;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@ExtendWith(TestListener.class)
@ExtendWith(TestlistnerApi.class)
public class TestWeb extends Abstract {

    public Map<String, Object> changes = new HashMap<>();
    public JsonPath response;
    public String str;

    public String TestWeb(String FileName, Map<String, Object> changes, boolean deleteYes, String[] delete) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(FileName)));

            // Парсинг содержимого файла в JSONObject
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonContent);

            DocumentContext jsonContext = com.jayway.jsonpath.JsonPath.parse(jsonObject);

            // Изменение значений по указанным JSONPath
            for (Map.Entry<String, Object> entry : changes.entrySet()) {
                jsonContext.set(entry.getKey(), entry.getValue());
            }

            if (deleteYes == true) {
                for (String path : delete) {
                    jsonContext.delete(path);
                }
            }

            String str = jsonContext.jsonString();

            return jsonContext.jsonString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    @DisplayName("Тест для проверки отправки файла")
    public void TestFileAdd() throws IOException, ParseException, InterruptedException {

        changes.put("$.firstname", "ТЕСТ");
        String [] pathDelete = {"$.bookingdates.checkout"};
        System.out.println(TestWeb("File/book.json", changes, true, pathDelete));

        JsonPath response1 = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .when()
                .body(TestWeb("File/book.json", changes, true, pathDelete))
                .post(("https://restful-booker.herokuapp.com/booking"))
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract().jsonPath();



    }
}
