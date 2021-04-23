package fd.se.btsplus.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.time.Period;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PeriodDeserializerTest {
    private final PeriodDeserializer periodDeserializer = new PeriodDeserializer();

    @Test
    void testDeserialize() {
        JsonParser jsonParser = mock(JsonParser.class);
        DeserializationContext deserializationContext = mock(DeserializationContext.class);
        try {
            when(jsonParser.getValueAsString()).thenReturn("2011-3-4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Period period = periodDeserializer.deserialize(jsonParser, deserializationContext);
            Assertions.assertEquals(2011, period.getYears());
            Assertions.assertEquals(3, period.getMonths());
            Assertions.assertEquals(4, period.getDays());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeserializeWithAssertOverflow() {
        JsonParser jsonParser = mock(JsonParser.class);
        DeserializationContext deserializationContext = mock(DeserializationContext.class);
        try {
            when(jsonParser.getValueAsString()).thenReturn("2011-3-4-5");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(AssertionError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                periodDeserializer.deserialize(jsonParser, deserializationContext);
            }
        });
    }

    @Test
    void testDeserializeWithAssertInsufficient() {
        JsonParser jsonParser = mock(JsonParser.class);
        DeserializationContext deserializationContext = mock(DeserializationContext.class);
        try {
            when(jsonParser.getValueAsString()).thenReturn("2011-3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(AssertionError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                periodDeserializer.deserialize(jsonParser, deserializationContext);
            }
        });
    }
}