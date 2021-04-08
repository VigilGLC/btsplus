package fd.se.btsplus.model.consts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fd.se.btsplus.model.consts.Constant.*;

public enum CreditLevel {
    LEVEL_1(1, Arrays.asList(FUND, STOCK, TERM)),
    LEVEL_2(2, Arrays.asList(FUND, TERM)),
    LEVEL_3(3, Collections.singletonList(TERM));

    public final List<String> affordable;
    private final int value;

    CreditLevel(int value, List<String> affordable) {
        this.value = value;
        this.affordable = Collections.unmodifiableList(affordable);
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

    public static CreditLevel evaluate(Double balance, Double loan) {
        if (balance == null) {
            balance = 0d;
        }
        if (loan == null) {
            loan = 0d;
        }
        double diff = balance - loan;
        if (diff >= 50_0000) {
            return LEVEL_1;
        }
        if (diff >= 0) {
            return LEVEL_2;
        }
        return LEVEL_3;
    }

    @JsonValue
    public int value() {
        return value;
    }

}
