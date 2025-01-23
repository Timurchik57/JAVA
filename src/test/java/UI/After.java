package UI;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.hc.core5.util.TextUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;

@ExtendWith(TestListener.class)
@ExtendWith(TestListenerApi.class)
@Epic("Тесты на вебе")
@Feature("Тесты После")
public class After extends Abstract{

    @Test
    @DisplayName("Запускаем тесты, которые упали")
    public void TestFiled() throws IOException {
        String str = "";
        if (TextUtils.isEmpty(remote_url_chrome)) {
            str = "FiledTests.bat";
        } else {
            str = "FiledTests.sh";
        }
        Runtime.getRuntime().exec("src/test/resources/" + str + " /C start");
    }

    @Test
    @DisplayName("Редактируем файл запуска упавших тестов")
    public void AfterFiledTest() throws IOException {
        String path = "";
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                path = "FiledTests.bat";
            } else {
                path = "FiledTests.sh";
            }

            String str = new String(
                    Files.readAllBytes(Paths.get("src/test/resources/" + path + ""))).replace("mvn test -Dtest=, ", "");

            String modifiedText = Arrays.stream(str.split(" "))
                    .reduce("", (result, word) -> result.contains(word) ? result : result + " " + word);

            String modifiedText1 = modifiedText.replace("mvn test -Dtest= ", "mvn test -Dtest=").substring(
                    1);

            new FileWriter("src/test/resources/" + path + "", false).close();

            FileWriter writer = new FileWriter("src/test/resources/" + path + "", true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            if (TextUtils.isEmpty(remote_url_chrome)) {
                bufferWriter.write("mvn test -Dtest=\"" + modifiedText1 + "\"");
            } else {
                bufferWriter.write("mvn test -Dtest=\"" + modifiedText1 + "\" -DUrlChrome=" + remote_url_chrome + "");
            }
            bufferWriter.close();
        }

        String text1 = new String(Files.readAllBytes(Paths.get("src/test/resources/"+path+"")));
        System.out.println("Данные из файла с упавшими тестами - " + text1);
    }

    @Test
    @DisplayName("Метод нужен для того, чтобы из нескольких файлов с упавшими тестами взять тесты и поместить в один")
    public void Universalocator() throws IOException {

        String content = null;
        try {
            for (int i = 1; i < 15; i++) {
                content = new String(Files.readAllBytes(Paths.get("src/test/resources/test" + i + ".txt")));
                FileWriter writer = new FileWriter("src/test/resources/Filed.sh", true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(content);
                bufferWriter.close();
            }
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }
    }
}
