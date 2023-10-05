package UI;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestlistnerApi implements TestWatcher {

    JAVATest javaTest = new JAVATest();

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Отработал метод полсе падения теста");
        javaTest.ReplaceWordMethod("File/file.txt", "ЧЕРЕЗ", "через");
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Отработал метод полсе успеха теста");
        javaTest.ReplaceWordMethod("File/file.txt", "ЧЕРЕЗ", "через");
    }
}
