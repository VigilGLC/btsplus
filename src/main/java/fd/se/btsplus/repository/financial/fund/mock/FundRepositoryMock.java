package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.repository.financial.fund.FundRepository;
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
public class FundRepositoryMock implements FundRepository {
    private static final String path = "json/financial/fund/funds.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<Fund> funds;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.funds = new HashSet<>(jsonUtils.readList(jsonStr, Fund.class));
    }

    @Override
    public List<Fund> findAll() {
        return new ArrayList<>(this.funds);
    }

    @Override
    public Fund findById(long id) {
        return this.funds.stream().filter(f -> Long.valueOf(id).equals(f.getId())).
                findFirst().orElse(null);
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends Fund> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Fund> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Fund> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Fund> findAllById(Iterable<Long> longs) {
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
    public void delete(Fund entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Fund> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>


}
