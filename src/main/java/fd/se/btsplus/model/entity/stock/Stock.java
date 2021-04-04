package fd.se.btsplus.model.entity.stock;

import fd.se.btsplus.model.entity.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Stock implements Product {
    @Id
    private Long id;
    private String name;
}
