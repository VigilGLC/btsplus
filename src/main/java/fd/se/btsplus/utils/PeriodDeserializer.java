package fd.se.btsplus.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Period;

public class PeriodDeserializer extends StdDeserializer<Period> {
    protected PeriodDeserializer(Class<?> vc) {
        super(vc);
    }

    public PeriodDeserializer() {
        this(null);
    }

    @Override
    public Period deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final String value = p.getValueAsString();
        final String[] parts = value.split("-");
        if (parts.length != 3) {
            return null;
        }
        final int years = Integer.parseInt(parts[0]);
        final int months = Integer.parseInt(parts[1]);
        final int days = Integer.parseInt(parts[2]);
        return Period.of(years, months, days);
    }
}
