package UI;

import UI.PageObject.SQL;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.hc.core5.util.TextUtils;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

abstract public class Abstract {

    public static WebDriver driver;
    public static ChromeOptions chromeOptions;
    public static SafariOptions safariOptions;
    public static FirefoxOptions foxOptions;
    public static WebDriverWait wait;
    public static WebDriverWait waitTime;
    public static WebDriverWait waitTime2;
    public static Actions actions;
    public Properties props;
    public SQL sql;
    public TestInfo testInfo;

    public static ByteArrayOutputStream buffer;
    public static String remote_url_chrome;

    public static boolean RebaseTest = true;
    public String StrRebase;

    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        //chromeOptions.setHeadless(true);
        //chromeOptions.addArguments("window-size=1920, 1080");
        driver = new ChromeDriver((chromeOptions));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
    }

    @BeforeEach
    public void init() throws IOException {
        sql = new SQL();
        sql.Connect();

        FileInputStream in = new FileInputStream("src/test/resources/my.properties");
        props = new Properties();
        props.load(in);
        in.close();
        setUp();
        terminal(); // Для записи данных из консоли
        InputClass(); // Для сохраниения названий методов и классов

        //Переменная для перезапуска тестов
        StrRebase = ReadProp("src/test/resources/my.properties", ReadProp("src/test/resources/my.properties", "methodName"));
        if (TextUtils.isEmpty(StrRebase)) {
            InputProp("src/test/resources/my.properties", ReadProp("src/test/resources/my.properties", "methodName"), "0");
        }
    }

    @Step("Метод для перезапуска упавшего теста")
    public static void RebaseTests () throws  IOException {
        String runTest = "mvn test -Dtest=\""+ReadProp("src/test/resources/my.properties", "className")+"#"+ReadProp("src/test/resources/my.properties", "methodName")+"\"";

        Charset charset = StandardCharsets.UTF_8;
        Path path = Paths.get("src/test/resources/RebaseTestLocal.bat");
        byte[] bytes = runTest.getBytes(charset);
        Files.write(path, bytes);

        Runtime.getRuntime().exec("src/test/resources/RebaseTestLocal.bat /C start");
    }

    /** Инициализация специальной переменной для сохранения названия класса теста */
    @BeforeEach
    public void GetMethod (TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @Attachment
    public static byte[] LogConsole(String name) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/resources", name));
    }

    @Step("Запись данных из консоли в файл")
    public void terminal() {
        buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);
        // После этой строки любой вывод будет сохраняться в buffer
        System.setOut(new PrintStream(teeStream));
    }

    /**
     * Метод для чтения переменной из property
     * @params FileName - путь до файла с переменными окружения, например (src/test/resources/my.properties)
     * @params NameProp - название переменной, из корой хотим взять значение
     * @params Input - значение переменной, которую хотим сохранить
     */
    @Step("Запись в Properties переменной {1} = {2}")
    public static void InputProp(String FileName, String NameProp, String Input) throws IOException {
        FileInputStream in = new FileInputStream(FileName);
        Properties props = new Properties();
        props.load(in);
        in.close();
        FileOutputStream out = new FileOutputStream(FileName);
        props.setProperty(NameProp, Input);
        props.store(out, null);
        out.close();
    }

    /**
     * Метод для чтения переменной из property
     * @params FileName - путь до файла с переменными окружения, например (src/test/resources/my.properties)
     * @params NameProp - название переменной, из корой хотим взять значение
     */
    @Step("Чтение переменной {1} из Properties")
    public static String ReadProp(String FileName, String NameProp) throws IOException {
        FileInputStream in = new FileInputStream(FileName);
        Properties props = new Properties();
        props.load(in);
        in.close();
        String Name = props.getProperty(NameProp);
        return Name;
    }
    @Step("Ожидание появления элемента {0}")
    public void WaitElement(By locator) {
        wait.until(visibilityOfElementLocated(locator));
    }

    @Step("Ожидание появления элемента {0}, за время - {1}")
    public void WaitElementTime(By locator, Integer time) {
        waitTime = new WebDriverWait(driver, time);
        waitTime.until(visibilityOfElementLocated(locator));
    }

    @Step("Условие появления элемента {0}, за время - {1}")
    public boolean IfElementTime(By locator, Integer time) {
        waitTime = new WebDriverWait(driver, time);
        try {
            waitTime.until(visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Элемента нет на странице");
            return false;
        }
    }

    @Step("Нажимаем на элемент")
    public void ClickElement(By locator) {
        WaitElement(locator);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        //Нажимаем на элемент
        element.click();
    }

    @Step("Нажимаем на элемент с ожиданием по вемени")
    public void ClickElementTime(By locator, Integer time) {
        WaitElementTime(locator, time);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    }

    @Step("Ввод текста под Shadow root")
    public static void inputWord(WebElement element, String word) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + word + "'", element);
        element.sendKeys(Keys.BACK_SPACE);
    }

    @Step("Возвращает значение из Shadow root")
    public static WebElement getShadow(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
        return shadowDom;
    }

    @Step("Замена текста в Файле")
    public void ReplaceMethod(String File, String Word, String Replace) throws IOException {
        File file = new File(File);
        File fileWrite = new File("File/testWrite.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileWrite));
        String ln;
        while ((ln = br.readLine()) != null) {
            bw.write(ln.replace(Word, Replace)
            );
        }
        br.close();
        bw.close();
        Files.move(fileWrite.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    @Step("Замена текста в файле 2 способ")
    public void ReplaceWordMethod(String File, String Word, String Replace) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        Path path = Paths.get(File);
        Files.write(
                path,
                new String(Files.readAllBytes(path), charset).replace(Word, Replace)
                        .getBytes(charset)
        );
    }

    @Step("Записать все cookie в файл")
    public void WriteAllCookie (Set<Cookie> cookies) {

        File cookieFile = new File("src/test/resources/cookie.txt");

        try(FileWriter writer = new FileWriter(cookieFile)) {
            for (Cookie cookie : cookies) {
                writer.write(cookie.getName() + "=" + cookie.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Использовать все cookie из файла")
    public void ReadAllCookie (String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String [] parts = line.split("=");
                if(parts.length == 2) {
                    String name = parts[0];
                    String value = parts[1];
                    driver.manage().addCookie(new Cookie(name, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Метод записи названия класса Теста")
    public void InputClass () throws IOException {
        //Запись класса
        String className = this.getClass().getSimpleName();

        //Запись названия метода
        String methodName = testInfo.getTestMethod().orElseThrow().getName();

        //Запись названия DisplayName теста
        String DisplayNameTest = testInfo.getDisplayName();


        InputProp("src/test/resources/my.properties","className", className);
        InputProp("src/test/resources/my.properties","methodName", methodName);
        InputProp("src/test/resources/my.properties","DisplayNameTest", DisplayNameTest);
    }

}
