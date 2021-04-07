package fd.se.btsplus.model.domain;

import fd.se.btsplus.model.entity.financial.IDaily;
import fd.se.btsplus.model.entity.financial.IProduct;
import fd.se.btsplus.model.entity.financial.IPurchase;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDatum {
    private IProduct product;
    private IDaily daily;
    private IPurchase purchase;
}
