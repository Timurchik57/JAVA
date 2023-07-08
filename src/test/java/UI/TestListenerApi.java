package UI;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListenerApi implements TestWatcher {
    JAVA java;

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        java = new JAVA();
        java.ReplaceWordMethod("File/test.txt", "ПРОСТО", "просто");
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        java = new JAVA();
        java.ReplaceWordMethod("File/test.txt", "ПРОСТО", "просто");
    }
}
