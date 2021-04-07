package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;

import java.util.Optional;

public class FundPurchaseRepositoryMock implements FundPurchaseRepository {

    //<editor-fold desc="useless">
    @Override
    public <S extends Fund> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Fund> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Fund> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Fund> findAll() {
        return null;
    }

    @Override
    public Iterable<Fund> findAllById(Iterable<Long> longs) {
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
    public void delete(Fund entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Fund> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
