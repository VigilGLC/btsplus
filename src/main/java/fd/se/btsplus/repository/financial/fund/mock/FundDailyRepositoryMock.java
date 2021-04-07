package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Profile("!prod")
@Component
public class FundDailyRepositoryMock implements FundDailyRepository {

    //<editor-fold desc="useless">
    @Override
    public <S extends FundDaily> S save(S entity) {
        return null;
    }

    @Override
    public <S extends FundDaily> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<FundDaily> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<FundDaily> findAll() {
        return null;
    }

    @Override
    public Iterable<FundDaily> findAllById(Iterable<Long> longs) {
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
    public void delete(FundDaily entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends FundDaily> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
