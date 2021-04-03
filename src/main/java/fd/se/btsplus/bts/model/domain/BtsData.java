package fd.se.btsplus.bts.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BtsData {
    //    private Object accountDtos []
    private String address;
    private String alternateName;
    private String alternatePhone;
    private String branchNum;
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    private int creator;
    private String email;
    private int id;
    private String idnumber;
    private int idtype;
    private String name;
    private String permanentAddress;
    private String phone;
    private int sex;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
//    updater: null
}
