package UI;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.IOException;

public class TestListenerApi implements TestWatcher {
    JAVATest javaTest;

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        javaTest = new JAVATest();
        try {
            javaTest.ReplaceWordMethod("File/test.txt", "ПРОСТО", "просто");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        javaTest = new JAVATest();
        try {
            javaTest.ReplaceWordMethod("File/test.txt", "ПРОСТО", "просто");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
