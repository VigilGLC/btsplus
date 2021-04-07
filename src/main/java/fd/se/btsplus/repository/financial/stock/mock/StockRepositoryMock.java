package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.repository.financial.stock.StockRepository;
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
public class StockRepositoryMock implements StockRepository {
    private static final String path = "json/financial/stock/stocks.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<Stock> stocks;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.stocks = new HashSet<>(jsonUtils.readList(jsonStr, Stock.class));
    }

    @Override
    public List<Stock> findAll() {
        return new ArrayList<>(this.stocks);
    }

    @Override
    public Stock findById(long id) {
        return this.stocks.stream().filter(s -> Long.valueOf(id).equals(s.getId())).
                findFirst().orElse(null);
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends Stock> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Stock> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Stock> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Stock> findAllById(Iterable<Long> longs) {
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
    public void delete(Stock entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Stock> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
