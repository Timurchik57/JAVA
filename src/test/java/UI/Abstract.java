package UI;

import UI.PageObject.SQL;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

abstract public class Abstract {

    public static EventFiringWebDriver driver;
    public static ChromeOptions chromeOptions;
    public static WebDriverWait wait;
    public static Actions actions;
    public SQL sql;
    public static String Count;

    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("window-size=1920, 1080");
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        //driver.manage().window().maximize();
        driver.register(new Custom());
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
    }

    @BeforeEach
    public void init() throws IOException {
        sql = new SQL();
        sql.Connect();
        setUp();
    }

    public void WaitElement(By locator) {
        wait.until(visibilityOfElementLocated(locator));
    }

    @Step("Нажимаем на элемент")
    public void ClickElement(By locator) {
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    }

    public static void inputWord(WebElement element, String word) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='"+word+"'", element);
        element.sendKeys(Keys.BACK_SPACE);
    }

    public static WebElement getShadow(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
        return shadowDom;
    }
}
