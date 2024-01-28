package UI;

import UI.PageObject.SQL;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

abstract public class Abstract {

   public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public SQL sql;
    public static String Browser = System.getProperty("browser");
    public static WebDriverManager webDriverManager;

    public static void setUp() {

        if (Browser.contains("Chrome")) {
            webDriverManager = WebDriverManager.chromedriver().browserInDocker();
        }
        else if (Browser.contains("FireFox")) {
            webDriverManager = WebDriverManager.firefoxdriver().browserInDocker();
        }

        driver = webDriverManager.create();
        driver.manage().window().setSize(new Dimension(1920,1080));
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
    }

    @BeforeEach
    public void init() {
        sql = new SQL();
        sql.Connect();
        setUp();
    }

    @Step("Ожидание появления эдемента {0}")
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

    @Step("Ввод текста под Shadow root")
    public static void inputWord(WebElement element, String word) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='"+word+"'", element);
        element.sendKeys(Keys.BACK_SPACE);
    }

    @Step("Возвращает значение из Shadow root")
    public static WebElement getShadow(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
        return shadowDom;
    }

    @Step("Замена текста в Файле")
    public void ReplaceMethod (String File, String Word, String Replace) throws IOException {
        File file = new File(File);
        File fileWrite = new File("File/testWrite.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileWrite));
        String ln;
        while((ln = br.readLine()) != null) {
            bw.write(ln.replace(Word, Replace)
            );
        }
        br.close();
        bw.close();
        Files.move(fileWrite.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @Step("Замена текста в файле 2 способ")
    public void ReplaceWordMethod(String File, String Word, String Replace) throws IOException, InterruptedException {
        Charset charset = StandardCharsets.UTF_8;
        Path path = Paths.get(File);
        Files.write(
                path,
                new String(Files.readAllBytes(path), charset).replace(Word, Replace)
                        .getBytes(charset)
        );
    }
}
