package UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

abstract public class Abstract{

    public static EventFiringWebDriver driver;
    public static ChromeOptions chromeOptions;
    public static WebDriverWait wait;
    public static Actions actions;

    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("window-size=1920, 1080");
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        //driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
    }

    @BeforeEach
    public void init(){
        setUp();
    }

    public void WaitElement(By locator){
        wait.until(visibilityOfElementLocated(locator));
    }

    public void ClickElement(By locator){
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    }
























//    /** метод для ожидания элемента */
//    public static void WaitElement (By locator) {
//        wait.until(visibilityOfElementLocated(locator));
//    }
//
//    /** метод Найти эелемент, показать его и нажать */
//    public static void ClickElement (By locator) throws InterruptedException {
//        WaitElement(locator);
//        WebElement element = driver.findElement(locator);
//        actions.moveToElement(element);
//        actions.perform();
//        Thread.sleep(1200);
//        element.click();
//    }
//
//    /** метод для ввода данных  */
//    public static void inputWord (By locator, String Word) {
//        WaitElement(locator);
//        WebElement element = driver.findElement(locator);
//        actions.moveToElement(element);
//        actions.perform();
//        element.sendKeys();
//    }
}
