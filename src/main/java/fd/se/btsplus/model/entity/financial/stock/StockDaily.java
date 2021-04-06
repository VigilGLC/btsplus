package fd.se.btsplus.model.entity.financial.stock;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
public class StockDaily {
    @Id
    private Long id;
    @ManyToOne
    private Stock stock;
    private LocalDate date;
    private Double price;
}
