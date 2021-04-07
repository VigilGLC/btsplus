package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Loan;
import fd.se.btsplus.repository.bts.LoanRepository;
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
public class LoanRepositoryMock implements LoanRepository {
    private static final String path = "json/bts/loans.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<Loan> loans;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.loans = new HashSet<>(jsonUtils.readList(jsonStr, Loan.class));
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(this.loans);
    }
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
