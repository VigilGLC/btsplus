package fd.se.btsplus.bts.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class AccountDto {
    private String accountNum;
    private int accountType;
    private String accountTypeName;
    private double balance;
    private String branchNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private long creator;
    private String customerCode;
    private long id;
    private int loanProduct;
    private String password;
    private int state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private String voucherName; // "银行卡"
    private int voucherType; // 6
}
