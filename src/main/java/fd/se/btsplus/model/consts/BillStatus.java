package fd.se.btsplus.model.consts;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BillStatus {
    UNPAID(1),
    PENALIZED(2),
    PAID(4);

    private final int value;

    BillStatus(int roleType) {
        this.value = roleType;
    }

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
