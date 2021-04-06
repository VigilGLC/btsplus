package fd.se.btsplus.model.entity.financial.term;


import fd.se.btsplus.model.entity.financial.IFinancialProduct;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class Term implements IFinancialProduct {
    @Id
    private Long id;
    private String name;
    private LocalDateTime createdTime;
}
