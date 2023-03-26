package UI;

import UI.PageObject.OpenYandex;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@ExtendWith(TestListener.class)
@Feature("Тесты связанные с погодой")
public class JAVA extends Abstract {

    OpenYandex openYandex;

    @Issue(value = "TEL-123")
    @Link(name = "Погода", url = "https://yandex.ru/pogoda/moscow?from=1")
    @Owner(value = "Иван Иванов")
    @DisplayName("Проверка времени в Москве")
    @Description("Allure Framework")
    @Test
    public void SuccessTest() throws InterruptedException {
        openYandex = new OpenYandex(driver);

        driver.get(openYandex.Yandex);
        WaitElement(openYandex.SearchBarWait);
        openYandex.SearchBar.sendKeys("Время в Москве");
        ClickElement(openYandex.SearchWait);
    }

    @Test
    public void FiledTest() {
        driver.get("https://ya.ru");
        WaitElement(openYandex.SearchBarWait);
        driver.findElement(By.xpath("//input[@placeholder='найдётся всё']")).sendKeys("Время в Москве");
        driver.findElement(By.xpath("//button[contains(.,'123456')]")).click();
    }
}
