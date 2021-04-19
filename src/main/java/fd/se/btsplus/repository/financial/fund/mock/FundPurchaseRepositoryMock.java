package fd.se.btsplus.repository.financial.fund.mock;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.fund.FundPurchase;
import fd.se.btsplus.repository.IRepositoryMock;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;
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

import static fd.se.btsplus.service.IDateService.dayAfter;
import static fd.se.btsplus.service.IDateService.dayEqual;

@Profile("!prod")
@Component
@RequiredArgsConstructor
public class FundPurchaseRepositoryMock implements FundPurchaseRepository, IRepositoryMock {
    private static final String PATH = "json/financial/fund/fundPurchase's.json";
    @NonNull
    private final ResourceUtils resourceUtils;
    @NonNull
    private final JsonUtils jsonUtils;
    private Set<FundPurchase> set;

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
        this.set = new HashSet<>(jsonUtils.readList(jsonStr, FundPurchase.class));
    }

    @Override
    public List<FundPurchase> findAll() {
        return new ArrayList<>(this.set);
    }

    @Override
    public List<FundPurchase> findByCustomerCode(String customerCode) {
        return this.set.stream().
                filter(fp -> fp.getCustomer().getCode().equals(customerCode)).
                collect(Collectors.toList());
    }

    @Override
    public List<FundPurchase> findByFundAndCurrDateAndEndDateAfter(Fund fund, Date currDate, Date nextDate) {
        return this.set.stream().
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
        this.set.add(entity);
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
