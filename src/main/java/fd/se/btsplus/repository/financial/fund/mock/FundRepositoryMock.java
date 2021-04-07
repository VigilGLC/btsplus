package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.repository.financial.fund.FundRepository;

import java.util.List;
import java.util.Optional;

public class FundRepositoryMock implements FundRepository {

    @Override
    public List<Fund> findAll() {
        return null;
    }

    @Override
    public Fund findById(long id) {
        return null;
    }

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
