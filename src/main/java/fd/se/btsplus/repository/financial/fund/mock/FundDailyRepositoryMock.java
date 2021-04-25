package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import fd.se.btsplus.repository.IRepositoryMock;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static fd.se.btsplus.service.IDateService.dayEqual;

@Profile("!prod")
@Component
@RequiredArgsConstructor
public class FundDailyRepositoryMock implements FundDailyRepository, IRepositoryMock {
    private static final String PATH = "json/financial/fund/fundDaily's.json";
    @NonNull
    private final ResourceUtils resourceUtils;
    @NonNull
    private final JsonUtils jsonUtils;
    private Set<FundDaily> set;

    private volatile long nextId;

    @SneakyThrows
    @PostConstruct
    @Override
    public void init() {
        init(PATH);
    }

    @SneakyThrows
    @Override
    public void init(String path) {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.set = new HashSet<>(jsonUtils.readList(jsonStr, FundDaily.class));
    }

    @Override
    public List<FundDaily> findAll() {
        return new ArrayList<>(this.set);
    }

    @Override
    public FundDaily findByFundAndDate(Fund fund, Date date) {
        return this.set.stream().
                filter(fd -> fd.getFund().equals(fund) && dayEqual(fd.getDate(), date)).
                findFirst().orElse(null);
    }

    @Override
    public List<FundDaily> findByDate(Date date) {
        return this.set.stream().
                filter(fd -> dayEqual(fd.getDate(), date)).
                collect(Collectors.toList());
    }

    @Override
    public synchronized <S extends FundDaily> S save(S entity) {
        if (entity.getId() != null) {
            return entity;
        }
        entity.setId(nextId++);
        this.set.add(entity);
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
