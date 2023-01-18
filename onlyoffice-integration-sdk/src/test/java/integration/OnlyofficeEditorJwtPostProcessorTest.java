package integration;

import base.processor.postprocessor.OnlyofficeEditorJwtPostProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.model.config.Config;
import core.processor.OnlyofficeEditorPostProcessor;
import core.security.OnlyofficeJwtSecurity;
import core.security.OnlyofficeJwtSecurityManager;
import exception.OnlyofficeProcessAfterRuntimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OnlyofficeEditorJwtPostProcessorTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OnlyofficeJwtSecurity jwtSecurity = new OnlyofficeJwtSecurityManager(objectMapper);
    private final OnlyofficeEditorPostProcessor editorPostProcessor = new OnlyofficeEditorJwtPostProcessor(jwtSecurity, "secret");

    @Test
    public void processNullConfigTest() {
        assertThrows(OnlyofficeProcessAfterRuntimeException.class, () -> this.editorPostProcessor.processAfter(null));
    }

    @Test
    public void processEmptyConfigTest() {
        assertDoesNotThrow(() -> this.editorPostProcessor.processAfter(
                Config
                        .builder()
                        .build()
        ));
    }

    @Test
    public void processConfigTest() {
        Config c = Config
                    .builder()
                    .type("asdc")
                    .build();
        this.editorPostProcessor.processAfter(c);
        assertNotNull(c.getToken());
    }
}