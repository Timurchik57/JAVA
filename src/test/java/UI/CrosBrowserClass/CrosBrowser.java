package UI.CrosBrowserClass;

import UI.Custom;
import UI.PageObject.IDEA;
import UI.PageObject.SQL;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CrosBrowser {

    public static EventFiringWebDriver driver;
    public static ChromeOptions chromeOptions;
    public static FirefoxOptions foxOptions;
    public static WebDriverWait wait;
    public static Actions actions;
    public Properties props;
    public SQL sql;
    public static String Browser;

    @Step("Запускаем тест через браузер = {0}")
    public void setUp(String browser) throws IOException {
        Browser = browser;
        if (Browser.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            chromeOptions = new ChromeOptions();
            //chromeOptions.setHeadless(true);
            //chromeOptions.addArguments("window-size=1920, 1080");
            driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        }
        if (Browser.contains("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            foxOptions = new FirefoxOptions();
            //foxOptions.setHeadless(true);
            //foxOptions.addArguments("window-size=1920, 1080");
            driver = new EventFiringWebDriver(new FirefoxDriver(foxOptions));
        }
        driver.manage().window().maximize();
        driver.register(new Custom());
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);

        sql = new SQL();
        sql.Connect();

        FileInputStream in = new FileInputStream("src/test/resources/my.properties");
        props = new Properties();
        props.load(in);
        in.close();
    }

    @DisplayName("Параметризованный тест")
    @ParameterizedTest
    @CsvSource({"Chrome", "FireFox"})
    public  void Browser (String value) throws IOException {
        IDEA idea = new IDEA(driver);

        setUp(value);

        driver.get(idea.Idea);
        System.out.println(value);
    }
}
