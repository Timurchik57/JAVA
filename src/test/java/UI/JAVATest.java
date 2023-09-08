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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import static io.restassured.RestAssured.given;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Epic("Тесты на вебе")
@Feature("Тесты на сайте IDEA")
public class JAVATest extends Abstract {

    OpenYandex openYandex;
    IDEA idea;
    public String ID;
    public String Text = System.getProperty("TextMvn");

    @Test
    @DisplayName("Тест для проверки Cherry-Pick")
    public void TestBranches() {
        //Захотел добавить этот текст локально
        System.out.println("Тест для проверки Cherry-Pick");
        System.out.println("Для 10 урока по Git");
    }

    @Test
    public void File2() throws IOException, InterruptedException {

        ReplaceWordMethod("File/test.txt", "просто", "ПРОСТО");

        String text1 = new String(Files.readAllBytes(Paths.get("File/test.txt")));

        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"firstname\" : \""+Text+"\",\n" +
                        "    \"lastname\" : \"Тестови\",\n" +
                        "    \"totalprice\" : 150,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Тестирование\"\n" +
                        "}")
                .post("https://restful-booker.herokuapp.com/booking")
                .prettyPeek()
                .body()
                .jsonPath();
        Assertions.assertEquals(response.get("booking.lastname"), "Тестови", "lastname е совпадает с Тестович");
    }

    @Test
    public void File() throws IOException {

        String text = "Это будет небольшой текст в переменной, нужен просто для проверки!";

        String text1 = new String(Files.readAllBytes(Paths.get("File/test.txt")));
        String newStr = text1.replace("проверки", "ПРОВЕРКИ");
        String newStr1 = newStr.replace("небольшой", "НЕБОЛЬШОЙ");
        System.out.println(newStr1);

        JsonPath response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"firstname\" : \""+newStr+"\",\n" +
                        "    \"lastname\" : \"Тестович\",\n" +
                        "    \"totalprice\" : 150,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Тестирование\"\n" +
                        "}")
                .post("https://restful-booker.herokuapp.com/booking")
                .prettyPeek()
                .body()
                .jsonPath();
    }

    @Issue(value = "TEL-123")
    @Link(name = "Идея", url = "https://www.jetbrains.com/idea/")
    @Owner(value = "Иван Иванов")
    @DisplayName("Сравнение Атрибутов")
    @Description("Переходим на страницу и берем атрибуты элемента, чтобы сравнить с ожидаемым результатом")
    @Test
    @Story("Позитивные тесты")
    public void Attribute() throws InterruptedException, SQLException {
        idea = new IDEA(driver);
        driver.get(idea.Idea);
        WaitElement(idea.Buy);
        WaitElement(idea.Header);

        String Attribute = driver.findElement(idea.Buy).getAttribute("aria-label");
        String CssAttribute = driver.findElement(idea.Header).getCssValue("font-size");

        System.out.println("Первый тест");
        Assertions.assertEquals(Attribute, "Navigate to Store", "Нужный атрибут не совпадает со значением Navigate to Store");

        System.out.println("Второй тест");
        Assertions.assertEquals(CssAttribute, "72px", "Нужный атрибут не совпадает со значением CSS ");

        Thread.sleep(30000);
    }

    @Issue(value = "TEL-124")
    @Link(name = "Идея", url = "https://www.jetbrains.com/idea/")
    @Owner(value = "Иван Иванов")
    @DisplayName("Определяем множество значений в List")
    @Description("Переходим на страницу и берём список значений, чтобы сравнить с ожидаемым результатом из БД")
    @Test
    @Story("Позитивные тесты")
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

//        List<String> listSql = new ArrayList<>();
//        sql.StartConnection("Select * from yourtable.test where id = 20259;");
//        while (sql.resultSet.next()) {
//            String str = sql.resultSet.getString("name");
//            listSql.add(str);
//        }
//
//        Assertions.assertEquals(list, listSql, "Значения на вебе и в БД не совпадают");
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
