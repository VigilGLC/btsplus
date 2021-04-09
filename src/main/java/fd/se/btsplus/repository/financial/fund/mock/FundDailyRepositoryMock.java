package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
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
import java.util.stream.Collectors;

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

    @Override
    public FundDaily findByFundAndDate(Fund fund, Date date) {
        return this.fundDailies.stream().
                filter(fd -> fd.getFund().equals(fund) && fd.getDate().equals(date)).
                findFirst().orElse(null);
    }

    @Override
    public List<FundDaily> findByDate(Date date) {
        return this.fundDailies.stream().
                filter(fd -> fd.getDate().equals(date)).
                collect(Collectors.toList());
    }

    @Override
    public <S extends FundDaily> S save(S entity) {
        this.fundDailies.remove(entity);
        this.fundDailies.add(entity);
        return entity;
    }

    @Override
    public <S extends FundDaily> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    //<editor-fold desc="useless">

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
