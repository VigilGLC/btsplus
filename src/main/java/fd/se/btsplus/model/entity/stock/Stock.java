package fd.se.btsplus.model.entity.stock;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Stock {
    @Id
    private Long id;
    private String name;
}
