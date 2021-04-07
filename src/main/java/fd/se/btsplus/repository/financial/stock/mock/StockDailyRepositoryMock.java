package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;

import java.util.Optional;

public class StockDailyRepositoryMock implements StockDailyRepository {
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
    public Iterable<StockDaily> findAll() {
        return null;
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
