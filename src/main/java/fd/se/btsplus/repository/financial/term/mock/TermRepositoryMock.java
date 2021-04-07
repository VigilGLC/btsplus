package fd.se.btsplus.repository.financial.term.mock;

import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.repository.financial.term.TermRepository;

import java.util.List;
import java.util.Optional;

public class TermRepositoryMock implements TermRepository {
    @Override
    public List<Term> findAll() {
        return null;
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends Term> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Term> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Term> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Term> findAllById(Iterable<Long> longs) {
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
    public void delete(Term entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Term> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
