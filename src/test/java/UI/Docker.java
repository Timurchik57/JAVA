package UI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(TestListener.class)
public class Docker extends Abstract {

//    WebDriver driver;
//    WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker().enableVnc().enableRecording();
//
//    @BeforeEach
//    void setupTest() {
//        driver = wdm.create();
//    }
//
//    @AfterEach
//    void teardown() {
//        wdm.quit();
//    }

//    @Test
//    void test() throws InterruptedException {
//        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
//
//        URL noVncUrl = wdm.getDockerNoVncUrl();
//       // assertThat(noVncUrl).isNotNull();
//
//        // Pause for manual inspection
//        Thread.sleep(Duration.ofSeconds(60).toMillis());
//
//        Path recordingPath = wdm.getDockerRecordingPath();
//      //  assertThat(recordingPath).exists();
//    }

    public void method() throws InterruptedException {
        driver.get("https://dzen.ru/suite/b37f597b-4094-45b9-a253-ebd2e3db3867");
        for (int i = 0; i < 5; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(
                    By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div[last()]")));
            WaitElement(By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div[last()]"));
            Thread.sleep(2000);
        }
    }

    public void methodPause() throws InterruptedException {
        ClickElement(By.xpath("//div[@class='zen-ui-video-video-player__control-toggle _is-controls-visible']"));
        Thread.sleep(2000);
        ClickElement(By.xpath("//div[@class='zen-ui-video-video-player__control-toggle _is-controls-visible']"));
    }

    void closeAllTabsExcept(String windowHandle) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(windowHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(windowHandle);
    }

    @Test
    public void SetVideo() throws InterruptedException {
        driver.get("https://dzen.ru/suite/b37f597b-4094-45b9-a253-ebd2e3db3867");
        for (int i = 0; i < 5; i++) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(
                    By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div[last()]")));
            WaitElement(By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div[last()]"));
            Thread.sleep(2000);
        }
        List<WebElement> listWeb = driver.findElements(
                By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div"));

        List<String> Quantitylistik = new ArrayList<>();
        for (int p = 0; p < listWeb.size(); p++) {
            Quantitylistik.add(driver.findElement(By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div["+p+"]//div[@class='card-layer-video-view__player-block']/a")).getText());
        }

        System.out.println(listWeb.size());
        for (int k = 1; k < listWeb.size() + 1; k++) {
            System.out.println(k + " Видео");
            ClickElement(By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div[" + k + "]"));
            Thread.sleep(120000);
            System.out.println("Нажимаем на лайк");
            String currentWindow = driver.getWindowHandle();
            closeAllTabsExcept(currentWindow);
            driver.get("https://dzen.ru/suite/b37f597b-4094-45b9-a253-ebd2e3db3867");
            ClickElement(By.xpath("//div[@class='feed _is-redesign _is-white-dzen _without-indent']/div["+k+"]//div[@class='card-video-2-view__footer-wrap']//button[@aria-label='Нравится']"));
        }
    }
}
