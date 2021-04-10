package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
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
import java.util.stream.Collectors;

import static fd.se.btsplus.service.IDateService.dayAfter;
import static fd.se.btsplus.service.IDateService.dayEqual;

@Profile("!prod")
@Component
@AllArgsConstructor
public class FundPurchaseRepositoryMock implements FundPurchaseRepository {
    private static final String path = "json/financial/fund/fundPurchase's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<FundPurchase> fundPurchases;

    private volatile long nextId;


    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.fundPurchases = new HashSet<>(jsonUtils.readList(jsonStr, FundPurchase.class));
        this.nextId = fundPurchases.stream().mapToLong(FundPurchase::getId).max().orElse(0L) + 1;
    }

    @Override
    public List<FundPurchase> findAll() {
        return new ArrayList<>(this.fundPurchases);
    }

    @Override
    public List<FundPurchase> findByCustomerCode(String customerCode) {
        return this.fundPurchases.stream().
                filter(fp -> fp.getCustomer().getCode().equals(customerCode)).
                collect(Collectors.toList());
    }

    @Override
    public List<FundPurchase> findByFundAndCurrDateAndEndDateAfter(Fund fund, Date currDate, Date nextDate) {
        return this.fundPurchases.stream().
                filter(fp -> fp.getFund().equals(fund) && dayEqual(fp.getCurrDate(), currDate)
                        && dayAfter(fp.getEndDate(), nextDate)).
                collect(Collectors.toList());
    }

    @Override
    public synchronized <S extends FundPurchase> S save(S entity) {
        if (entity.getId() != null) {
            return entity;
        }
        entity.setId(nextId++);
        this.fundPurchases.add(entity);
        return entity;
    }

    @Override
    public <S extends FundPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    //<editor-fold desc="useless">

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
