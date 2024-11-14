package UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;


import static UI.Abstract.*;

public class TestListener implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

        if (Browser.contains("Chrome")) {
            Allure.addAttachment("Логи после падения теста: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
        } else {
            WebDriverManager.firefoxdriver().quit();
        }
        // После каждого теста, сохраняем название его класса, чтобы в дальнейшем перезапустить его
        if (ReadProp("src/test/resources/my.properties", "IfCountListner").contains("web") == true) {
            InputClassFile();
        }
        driver.quit();
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

        if (Browser.contains("Chrome")) {
            Allure.addAttachment("Логи после падения теста: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
        } else {
            WebDriverManager.firefoxdriver().quit();
        }
        driver.quit();
    }
}
