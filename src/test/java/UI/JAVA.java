package UI;

import UI.PageObject.IDEA;
import UI.PageObject.OpenYandex;
import io.qameta.allure.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import java.io.IOException;
import java.sql.SQLException;

@ExtendWith(TestListener.class)
@Feature("Тесты связанные с погодой")
public class JAVA extends Abstract {

    OpenYandex openYandex;
    IDEA idea;

    @Issue(value = "TEL-123")
    @Link(name = "Погода", url = "https://yandex.ru/pogoda/moscow?from=1")
    @Owner(value = "Иван Иванов")
    @DisplayName("Проверка Базы данных")
    @Description("Allure Framework")
    @Test
    public void SuccessTest() throws InterruptedException, SQLException, IOException {
        openYandex = new OpenYandex(driver);
        idea = new IDEA(driver);

//        driver.get(idea.Idea);
//        ClickElement(idea.DeveloperTools);
//        ClickElement(idea.WebStorm);
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
    @DisplayName("Проверка времени в Москве")
    public void FiledTest() {
        driver.get("https://ya.ru");
        WaitElement(openYandex.SearchBarWait);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        driver.findElement(By.xpath("//button[contains(.,'123456')]")).click();
    }
}
