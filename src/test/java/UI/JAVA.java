package UI;

import UI.PageObject.IDEA;
import UI.PageObject.OpenYandex;
import io.qameta.allure.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
@ExtendWith(TestListener.class)
public class JAVA extends Abstract {

    OpenYandex openYandex;
    IDEA idea;
    public String ID;

    @Test
    @DisplayName("Определяем множество значений в List")
    public void List() throws InterruptedException, SQLException {
        idea = new IDEA(driver);

        driver.get(idea.Idea);
        ClickElement(idea.DeveloperTools);
        Thread.sleep(1000);

        List<String> list = new ArrayList<>();
        List<WebElement> WebList = driver.findElements(idea.ListIDEs);
        for (int i = 0; i < WebList.size(); i++) {
            list.add(WebList.get(i).getText());
        }

        List<String> listSql = new ArrayList<>();
        sql.StartConnection("Select * from yourtable.test where id = 20259;");
        while (sql.resultSet.next()) {
            String str = sql.resultSet.getString("name");
            listSql.add(str);
        }

        Assertions.assertEquals(list, listSql, "Значения на вебе и в БД не совпадают");
    }

    @Test
    public void ShadowDoom() throws InterruptedException, SQLException, IOException {
        idea = new IDEA(driver);

        driver.get(idea.Idea);
        ClickElement(idea.OpenSearch);
        WaitElement(idea.Search);

        inputWord(driver.findElement(idea.Search), "Тест ");

        WebElement web = driver.findElement(idea.Search);
        WebElement shadow1 = getShadow(web, driver);
        String str = shadow1.findElement(idea.Search).getText();
        System.out.println(str);

        driver.findElement(idea.Search).sendKeys("1234dfhg");
    }

    @Issue(value = "TEL-123")
    @Link(name = "Погода", url = "https://yandex.ru/pogoda/moscow?from=1")
    @Owner(value = "Иван Иванов")
    @DisplayName("Проверка API")
    @Description("Отправляем запросы и сравниваем результат")
    @Test
    public void SuccessTest() throws InterruptedException, SQLException, IOException {
        openYandex = new OpenYandex(driver);
        idea = new IDEA(driver);

        driver.get(idea.Idea);
        ClickElement(idea.DeveloperTools);
        ClickElement(idea.WebStorm);
        Assertions.assertEquals(1, 2);

        sql.StartConnection("Select * from yourtable.test where id = 20259;");
        while (sql.resultSet.next()) {
            String str = sql.resultSet.getString("doctype");
            System.out.println(str);
            Assertions.assertEquals(str, "SMSV1", "Не совадает значение doctype");
        }

        sql.UpdateConnection("Update testBD set name = 'ТЕСТ', qwerty = '1234' where id = '1';");
        sql.UpdateConnection("Insert into testBD (name, qwerty) value ('алекс', '0987');");
    }


    @Test
    @DisplayName("Проверка по id")
    public void SuccessTest1() {
        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .header("Authorization", "Bearer ")
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                              "    \"name\": \"Ivan\",\n" +
                              "    \"job\": \"Ivanov\"\n" +
                              "}")
                .post("https://reqres.in/api/users")
                .prettyPeek()
                .body()
                .jsonPath();
        ID = response.get("id");
        System.out.println(ID);
    }

    @Order(3)
    @Test
    @DisplayName("Проверка по id")
    public void FiledTest2() {
        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .header("Authorization", "Bearer ")
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .prettyPeek()
                .body()
                .jsonPath();
    }
}
