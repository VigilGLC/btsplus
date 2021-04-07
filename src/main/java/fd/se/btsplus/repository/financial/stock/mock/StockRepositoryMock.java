package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.repository.financial.stock.StockRepository;

import java.util.List;
import java.util.Optional;

public class StockRepositoryMock implements StockRepository {
    @Override
    public List<Stock> findAll() {
        return null;
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
