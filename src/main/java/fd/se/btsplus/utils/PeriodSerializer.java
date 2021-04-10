package fd.se.btsplus.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PeriodSerializer extends JsonSerializer<Period> {

    @Override
    public void serialize(Period value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        List<String> parts = new ArrayList<>();
        parts.add(String.valueOf(value.getYears()));
        parts.add(String.valueOf(value.getMonths()));
        parts.add(String.valueOf(value.getDays()));
        final String result = String.join("-", parts);
        gen.writeString(result);
    }
}
