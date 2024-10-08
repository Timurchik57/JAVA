package UI.CrosBrowserClass;

import UI.PageObject.IDEA;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static UI.CrosBrowserClass.CrosBrowser.driver;


@Epic("Тесты UI на разных браузерах")
@Feature("Направления")
public class CrosBrowserTest {

    IDEA idea;
    CrosBrowser crosBrowser;

    @DisplayName("Кроссбраузерность")
    @ParameterizedTest
    @CsvSource({"Chrome", "FireFox"})
    public  void Browser (String value) throws IOException {
        crosBrowser = new CrosBrowser();

        crosBrowser.setUp(value);
        driver.get(idea.Idea);

        System.out.println(value);
    }

    @DisplayName("Кроссбраузерность")
    @Test
    public  void Browser2 (String value) throws IOException {
        idea = new IDEA(driver);
        crosBrowser = new CrosBrowser();

        crosBrowser.setUp(value);
        driver.get("https://www.jetbrains.com/idea/");

        System.out.println(value);
    }
}
