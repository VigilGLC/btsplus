package fd.se.btsplus.model.consts;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Role {
    TELLER(1),
    ADMIN(2),
    ANY(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role of(int roleType) {
        for (Role role : Role.values()) {
            if (role.value == roleType) {
                return role;
            }
        }
        return null;
    }

    @JsonValue
    public int value() {
        return value;
    }

    public boolean satisfy(Role role) {
        if (role == null) {
            return true;
        }
        return (this.value & role.value) != 0;
    }
}
