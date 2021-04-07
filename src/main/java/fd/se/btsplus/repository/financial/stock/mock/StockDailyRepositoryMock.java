package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Profile("!prod")
@Component
@AllArgsConstructor
public class StockDailyRepositoryMock implements StockDailyRepository {

    private static final String path = "json/financial/stock/stockDaily's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<StockDaily> stockDailies;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.stockDailies = new HashSet<>(jsonUtils.readList(jsonStr, StockDaily.class));
    }

    @Override
    public List<StockDaily> findAll() {
        return new ArrayList<>(this.stockDailies);
    }

    //<editor-fold desc="useless">

    @Override
    public <S extends StockDaily> S save(S entity) {
        return null;
    }

    @Override
    public <S extends StockDaily> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<StockDaily> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<StockDaily> findAllById(Iterable<Long> longs) {
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
    public void delete(StockDaily entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends StockDaily> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
