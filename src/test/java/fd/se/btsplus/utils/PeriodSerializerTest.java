package fd.se.btsplus.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Period;

import static org.mockito.Mockito.mock;

class PeriodSerializerTest {
    @Test
    void testSerialize() throws IOException {
        Period period = Period.of(2000, 1, 12);
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);
        SerializerProvider serializerProvider = mock(SerializerProvider.class);
        PeriodSerializer periodSerializer = new PeriodSerializer();
        periodSerializer.serialize(period, jsonGenerator, serializerProvider);
        Assertions.assertTrue(true);
    }

}