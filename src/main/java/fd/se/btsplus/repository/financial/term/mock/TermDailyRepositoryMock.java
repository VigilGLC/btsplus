package fd.se.btsplus.repository.financial.term.mock;

import fd.se.btsplus.model.entity.financial.term.TermDaily;
import fd.se.btsplus.repository.financial.term.TermDailyRepository;

import java.util.Optional;

public class TermDailyRepositoryMock implements TermDailyRepository {

    //<editor-fold desc="useless">
    @Override
    public <S extends TermDaily> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TermDaily> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TermDaily> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TermDaily> findAll() {
        return null;
    }

    @Override
    public Iterable<TermDaily> findAllById(Iterable<Long> longs) {
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
    public void delete(TermDaily entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TermDaily> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
