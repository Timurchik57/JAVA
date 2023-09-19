package UI;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

public class DockerTest {

    WebDriver driver;
    WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker().enableVnc().enableRecording();

    @BeforeEach
    void setupTest() {
        driver = wdm.create();
    }

    @AfterEach
    void teardown() {
        wdm.quit();
    }

    @Test
    void test() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        URL noVncUrl = wdm.getDockerNoVncUrl();
       // assertThat(noVncUrl).isNotNull();

        // Pause for manual inspection
        Thread.sleep(Duration.ofSeconds(60).toMillis());

        Path recordingPath = wdm.getDockerRecordingPath();
      //  assertThat(recordingPath).exists();
    }
}
