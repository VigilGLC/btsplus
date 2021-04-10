package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
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

import static fd.se.btsplus.service.IDateService.dayEqual;

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

    @Override
    public List<StockPurchase> findByStockAndCurrDate(Stock stock, Date lastDate) {
        return this.stockPurchases.stream().
                filter(sp -> sp.getStock().equals(stock) && dayEqual(sp.getCurrDate(), lastDate)).
                collect(Collectors.toList());
    }

    @Override
    public <S extends StockPurchase> S save(S entity) {
        this.stockPurchases.remove(entity);
        this.stockPurchases.add(entity);
        return entity;
    }

    @Override
    public <S extends StockPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }
    //<editor-fold desc="useless">

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
