package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.FundPurchase;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;
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
public class FundPurchaseRepositoryMock implements FundPurchaseRepository {
    private static final String path = "json/financial/fund/fundPurchase's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<FundPurchase> fundPurchases;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.fundPurchases = new HashSet<>(jsonUtils.readList(jsonStr, FundPurchase.class));
    }

    @Override
    public List<FundPurchase> findAll() {
        return new ArrayList<>(this.fundPurchases);
    }

    //<editor-fold desc="useless">

    @Override
    public <S extends FundPurchase> S save(S entity) {
        return null;
    }

    @Override
    public <S extends FundPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<FundPurchase> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<FundPurchase> findAllById(Iterable<Long> longs) {
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
    public void delete(FundPurchase entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends FundPurchase> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>

}
