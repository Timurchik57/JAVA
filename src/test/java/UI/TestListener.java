package UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static UI.Abstract.*;

public class TestListener implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

        try {
            if (Browser.contains("Chrome")) {
                Allure.addAttachment("Логи после успешного теста: ",
                        String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
                WebDriverManager.chromedriver().quit();
            } else {
                WebDriverManager.firefoxdriver().quit();
            }
            // После каждого теста, сохраняем название его класса, чтобы в дальнейшем перезапустить его
            if (ReadProp("src/test/resources/my.properties", "IfCountListner").equals("web") &
                    !ReadProp("src/test/resources/my.properties", "methodName").equals("TestFiled")) {
                InputClassFile();
            }

            // Сохраняем лог консоли в файл
            try (OutputStream fileStream = new FileOutputStream("src/test/resources/console.txt")) {
                buffer.writeTo(fileStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Allure.addAttachment("Данные из консоли: ",
                    new String(Files.readAllBytes(Paths.get("src/test/resources/console.txt"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

        if (Browser.contains("Chrome")) {
            Allure.addAttachment("Логи после падения теста: ",
                    String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
        } else {
            WebDriverManager.firefoxdriver().quit();
        }

        // Сохраняем лог консоли в файл
        try (OutputStream fileStream = new FileOutputStream("src/test/resources/console.txt")) {
            buffer.writeTo(fileStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Allure.addAttachment("Данные из консоли: ",
                    new String(Files.readAllBytes(Paths.get("src/test/resources/console.txt"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}
