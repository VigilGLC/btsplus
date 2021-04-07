package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Loan;
import fd.se.btsplus.repository.bts.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Profile("!prod")
@Component
@AllArgsConstructor
public class LoanRepositoryMock implements LoanRepository {
    //<editor-fold desc="useless">
    @Override
    public <S extends Loan> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Loan> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Loan> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Loan> findAll() {
        return null;
    }

    @Override
    public Iterable<Loan> findAllById(Iterable<Long> longs) {
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
    public void delete(Loan entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Loan> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
