package UI;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListenerApi implements TestWatcher {
    JAVATest javaTest;

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        javaTest = new JAVATest();
        javaTest.ReplaceWordMethod("File/test.txt", "ПРОСТО", "просто");
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        javaTest = new JAVATest();
        javaTest.ReplaceWordMethod("File/test.txt", "ПРОСТО", "просто");
    }
}
