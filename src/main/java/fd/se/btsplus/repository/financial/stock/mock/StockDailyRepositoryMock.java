package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static fd.se.btsplus.service.IDateService.dayEqual;

@Profile("!prod")
@Component
@RequiredArgsConstructor
public class StockDailyRepositoryMock implements StockDailyRepository {
    private static final String path = "json/financial/stock/stockDaily's.json";
    @NonNull
    private final ResourceUtils resourceUtils;
    @NonNull
    private final JsonUtils jsonUtils;
    private Set<StockDaily> stockDailies;

    private volatile long nextId;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.stockDailies = new HashSet<>(jsonUtils.readList(jsonStr, StockDaily.class));
        this.nextId = stockDailies.stream().mapToLong(StockDaily::getId).max().orElse(0L) + 1;
    }

    @Override
    public List<StockDaily> findAll() {
        return new ArrayList<>(this.stockDailies);
    }

    @Override
    public StockDaily findByStockAndDate(Stock stock, Date date) {
        return this.stockDailies.stream().
                filter(sd -> sd.getStock().equals(stock) && dayEqual(sd.getDate(), date)).
                findFirst().orElse(null);
    }

    @Override
    public List<StockDaily> findByDate(Date date) {
        return this.stockDailies.stream().
                filter(sd -> dayEqual(sd.getDate(), date)).
                collect(Collectors.toList());
    }

    @Override
    public synchronized <S extends StockDaily> S save(S entity) {
        if (entity.getId() != null) {
            return entity;
        }
        entity.setId(nextId++);
        this.stockDailies.add(entity);
        return entity;
    }

    @Override
    public <S extends StockDaily> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }
    //<editor-fold desc="useless">

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
