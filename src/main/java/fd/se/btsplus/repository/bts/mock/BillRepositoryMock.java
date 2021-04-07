package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.repository.bts.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Profile("!prod")
@Component
@AllArgsConstructor
public class BillRepositoryMock implements BillRepository {

    //<editor-fold desc="useless">
    @Override
    public <S extends Bill> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Bill> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Bill> findAll() {
        return null;
    }

    @Override
    public Iterable<Bill> findAllById(Iterable<Long> longs) {
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
    public void delete(Bill entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Bill> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
