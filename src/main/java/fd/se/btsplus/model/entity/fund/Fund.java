package fd.se.btsplus.model.entity.fund;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Fund {
    @Id
    private Long id;
    private String name;
}
