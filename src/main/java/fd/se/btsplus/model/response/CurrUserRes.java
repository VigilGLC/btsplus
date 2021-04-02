package fd.se.btsplus.model.response;

import fd.se.btsplus.bts.model.domain.BtsRole;
import fd.se.btsplus.bts.model.response.BtsCurrUserRes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CurrUserRes extends BaseRes {
    @Schema(description = "id")
    private long id;
    @Schema(description = "creator")
    private long creator;
    @Schema(description = "username")
    private String username;
    @Schema(description = "nickname")
    private String nickname;
    @Schema(description = "sex")
    private int sex;
    @Schema(description = "phone")
    private String phone;
    @Schema(description = "email")
    private String email;
    @Schema(description = "address")
    private String address;
    @Schema(description = "branch name")
    private String branchNum;
    @Schema(description = "status")
    private int status;
    @Schema(description = "roles")
    private List<BtsRole> roles;


    public static CurrUserRes from(BtsCurrUserRes res) {
        return new CurrUserRes(res.getId(),
                res.getCreator(),
                res.getUsername(),
                res.getNickname(),
                res.getSex(),
                res.getPhone(),
                res.getEmail(),
                res.getAddress(),
                res.getBranchNum(),
                res.getStatus(),
                res.getRoles());
    }

}
