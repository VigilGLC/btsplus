package fd.se.btsplus.model.entity.fund;

import fd.se.btsplus.model.entity.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Fund implements Product {
    @Id
    private Long id;
    private String name;
}
