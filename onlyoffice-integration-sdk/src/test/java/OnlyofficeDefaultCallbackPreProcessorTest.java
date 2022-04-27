import base.processor.preprocessor.OnlyofficeDefaultCallbackPreProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import core.model.OnlyofficeModelMutator;
import core.model.callback.Callback;
import core.processor.preprocessor.OnlyofficeCallbackPreProcessor;
import core.runner.implementation.CallbackRequest;
import core.security.OnlyofficeJwtSecurity;
import core.security.OnlyofficeJwtSecurityManager;
import exception.OnlyofficeProcessBeforeRuntimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OnlyofficeDefaultCallbackPreProcessorTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OnlyofficeJwtSecurity jwtSecurity = new OnlyofficeJwtSecurityManager(objectMapper);
    private final OnlyofficeCallbackPreProcessor callbackOnlyofficePreProcessor = new OnlyofficeDefaultCallbackPreProcessor(objectMapper);

    @Test
    public void processNullCallbackRequestParameterIgnoreTest() {
        assertThrows(OnlyofficeProcessBeforeRuntimeException.class, () -> this.callbackOnlyofficePreProcessor.run(null));
    }

    @Test
    public void processNoCallbackParameterIgnoreTest() {
        assertDoesNotThrow(() -> this.callbackOnlyofficePreProcessor.run(
                CallbackRequest
                        .builder()
                        .callback(null)
                        .build()
        ));
    }

    @Test
    public void processNoPreProcessorParametersIgnoreTest() {
        assertDoesNotThrow(() -> this.callbackOnlyofficePreProcessor.run(
                CallbackRequest
                        .builder()
                        .callback(
                                Callback
                                        .builder()
                                        .key("1234")
                                        .status(2)
                                        .build()
                        )
                        .build()
        ));
    }

    @Test
    public void processNullMapPreProcessorParametersIgnoreTest() {
        assertDoesNotThrow(() -> this.callbackOnlyofficePreProcessor.run(
                CallbackRequest
                        .builder()
                        .callback(
                                Callback
                                        .builder()
                                        .key("1234")
                                        .status(2)
                                        .build()
                        )
                        .build()
                        .addPreProcessor("testing", null)
        ));
    }

    @Test
    public void processEmptyMapPreProcessorParametersIgnoreTest() {
        assertDoesNotThrow(() -> this.callbackOnlyofficePreProcessor.run(
                CallbackRequest
                        .builder()
                        .callback(
                                Callback
                                        .builder()
                                        .key("1234")
                                        .status(2)
                                        .build()
                        )
                        .build()
                        .addPreProcessor("onlyoffice.preprocessor.default.callback", ImmutableMap.of())
        ));
    }

    @Test
    public void processMalformedTokenTest() {
        String token = "aasdasc.asvasvas.arqwrtqwtrqw";
        assertThrows(OnlyofficeProcessBeforeRuntimeException.class, () -> this.callbackOnlyofficePreProcessor.run(
                CallbackRequest
                        .builder()
                        .callback(
                                Callback
                                        .builder()
                                        .key("1234")
                                        .status(2)
                                        .token(token)
                                        .build()
                        )
                        .build()
                        .addPreProcessor("onlyoffice.preprocessor.default.callback", ImmutableMap.of(
                                "key", "secret",
                                "token", token
                        ))
        ));
    }

    @Test
    public void processValidWithAutoFillerTest() {
        class T implements OnlyofficeModelMutator<Callback> {
            private int status;

            public T() {
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void mutate(Callback model) {
                model.setStatus(status);
            }
        }

        Date date = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
        String token = this.jwtSecurity.sign(Map.of("status", 3), "secret", date).get();
        Callback callback = Callback
                .builder()
                .key("1234")
                .status(2)
                .build();
        assertDoesNotThrow(() -> this.callbackOnlyofficePreProcessor.run(
                CallbackRequest
                        .builder()
                        .callback(callback)
                        .build()
                        .addPreProcessor("onlyoffice.preprocessor.default.callback", ImmutableMap.of(
                                "key", "secret",
                                "token", token,
                                "mutator", new T()
                        ))
        ));
        assertEquals(3, callback.getStatus());
    }
}
