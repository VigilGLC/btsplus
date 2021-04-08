package fd.se.btsplus.model.entity.financial.fund;

import fd.se.btsplus.model.entity.financial.IProduct;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@RequiredArgsConstructor
public class Fund implements IProduct {
    @Id
    private Long id;
    private String name;
    private Date createdTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return id.equals(fund.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
