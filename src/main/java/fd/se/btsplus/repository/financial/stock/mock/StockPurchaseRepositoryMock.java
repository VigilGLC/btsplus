package fd.se.btsplus.repository.financial.stock.mock;

import fd.se.btsplus.model.entity.financial.stock.StockPurchase;
import fd.se.btsplus.repository.financial.stock.StockPurchaseRepository;

import java.util.Optional;

public class StockPurchaseRepositoryMock implements StockPurchaseRepository {

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
    public Iterable<StockPurchase> findAll() {
        return null;
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
