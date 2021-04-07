package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
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
public class FundDailyRepositoryMock implements FundDailyRepository {
    private static final String path = "json/financial/fund/fundDaily's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<FundDaily> fundDailies;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.fundDailies = new HashSet<>(jsonUtils.readList(jsonStr, FundDaily.class));
    }

    @Override
    public List<FundDaily> findAll() {
        return new ArrayList<>(this.fundDailies);
    }

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
