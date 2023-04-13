package UI;

import UI.PageObject.IDEA;
import UI.PageObject.OpenYandex;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

@ExtendWith(TestListener.class)
@Feature("Тесты связанные с погодой")
public class JAVA extends Abstract{

    OpenYandex openYandex;
    IDEA idea;

    @Rule
    public RetryRule  rule = new RetryRule(2);

    @Issue(value = "TEL-123")
    @Link(name = "Погода", url = "https://yandex.ru/pogoda/moscow?from=1")
    @Owner(value = "Иван Иванов")
    @DisplayName("Проверка времени в Москве")
    @Description("Allure Framework")
    @RepeatedIfExceptionsTest(repeats = 1)
    public void SuccessTest() throws InterruptedException {
        openYandex = new OpenYandex(driver);
        idea = new IDEA(driver);

        driver.get(idea.Idea);
        ClickElement(idea.DeveloperTools);
        ClickElement(idea.WebStorm);
    }

    @Test
    @DisplayName("Проверка времени в Москве")
    public void FiledTest2() {
        Assertions.assertEquals(1, 2);
    }

    @RepeatedIfExceptionsTest(repeats = 1)
    @DisplayName("Проверка времени в Москве")
    public void FiledTest() {
        driver.get("https://ya.ru");
        WaitElement(openYandex.SearchBarWait);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        driver.findElement(By.xpath("//button[contains(.,'123456')]")).click();
    }
}
