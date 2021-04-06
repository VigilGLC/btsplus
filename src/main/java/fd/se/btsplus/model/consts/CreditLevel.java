package fd.se.btsplus.model.consts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditLevel {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3);

    private final int value;

    CreditLevel(int value) {
        this.value = value;
    }

    @JsonCreator
    public static CreditLevel of(int value) {
        for (CreditLevel level : CreditLevel.values()) {
            if (level.value == value) {
                return level;
            }
        }
        return null;
    }

    @JsonValue
    public int value() {
        return value;
    }

}
