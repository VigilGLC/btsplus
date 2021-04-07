package fd.se.btsplus.model.entity.financial.stock;

import fd.se.btsplus.model.entity.financial.IProduct;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class Stock implements IProduct {
    @Id
    private Long id;
    private String name;
    private Date createdTime;
}
