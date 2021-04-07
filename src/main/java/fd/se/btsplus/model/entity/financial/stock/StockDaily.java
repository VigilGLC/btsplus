package fd.se.btsplus.model.entity.financial.stock;

import fd.se.btsplus.model.entity.financial.IDaily;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class StockDaily implements IDaily {
    @Id
    private Long id;
    @ManyToOne
    private Stock stock;
    private Date date;
    private Double price;
}
