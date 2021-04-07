package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.repository.bts.BillRepository;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Profile("!prod")
@Component
@AllArgsConstructor
public class BillRepositoryMock implements BillRepository {
    private static final String path = "json/bts/bills.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<Bill> bills;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.bills = new HashSet<>(jsonUtils.readList(jsonStr, Bill.class));
    }

    @Override
    public List<Bill> findAll() {
        return new ArrayList<>(this.bills);
    }
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
