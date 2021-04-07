package fd.se.btsplus.repository.financial.term.mock;

import fd.se.btsplus.model.entity.financial.term.TermPurchase;
import fd.se.btsplus.repository.financial.term.TermPurchaseRepository;

import java.util.Optional;

public class TermPurchaseRepositoryMock implements TermPurchaseRepository {

    //<editor-fold desc="useless">
    @Override
    public <S extends TermPurchase> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TermPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TermPurchase> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TermPurchase> findAll() {
        return null;
    }

    @Override
    public Iterable<TermPurchase> findAllById(Iterable<Long> longs) {
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
    public void delete(TermPurchase entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TermPurchase> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
