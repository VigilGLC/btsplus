package fd.se.btsplus.model.consts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BillStatus {
    UNPAID_BEFORE(1),
    UNPAID_PENALIZED(2),
    UNPAID_AFTER(3),
    PAID(4);

    private final int value;

    BillStatus(int value) {
        this.value = value;
    }

    @JsonCreator
    public static BillStatus of(int value) {
        for (BillStatus status : BillStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public int value() {
        return value;
    }
}
