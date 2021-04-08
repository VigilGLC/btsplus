package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.StockPurchase;
import fd.se.btsplus.repository.financial.stock.StockPurchaseRepository;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Profile("!prod")
@Component
@AllArgsConstructor
public class StockPurchaseRepositoryMock implements StockPurchaseRepository {
    private static final String path = "json/financial/stock/stockPurchase's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<StockPurchase> stockPurchases;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.stockPurchases = new HashSet<>(jsonUtils.readList(jsonStr, StockPurchase.class));
    }

    @Override
    public List<StockPurchase> findAll() {
        return new ArrayList<>(this.stockPurchases);
    }

    @Override
    public List<StockPurchase> findByCustomerCode(String customerCode) {
        return this.stockPurchases.stream().
                filter(sp -> sp.getCustomer().getCode().equals(customerCode)).
                collect(Collectors.toList());
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends StockPurchase> S save(S entity) {
        return null;
    }

    @Override
    public <S extends StockPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<StockPurchase> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public Iterable<StockPurchase> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(StockPurchase entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends StockPurchase> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
