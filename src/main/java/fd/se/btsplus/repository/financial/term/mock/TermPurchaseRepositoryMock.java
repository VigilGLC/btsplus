package fd.se.btsplus.repository.financial.term.mock;

import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.model.entity.financial.term.TermPurchase;
import fd.se.btsplus.repository.financial.term.TermPurchaseRepository;
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
public class TermPurchaseRepositoryMock implements TermPurchaseRepository {
    private static final String path = "json/financial/term/termPurchase's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<TermPurchase> termPurchases;

    private volatile long nextId;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.termPurchases = new HashSet<>(jsonUtils.readList(jsonStr, TermPurchase.class));
        this.nextId = this.termPurchases.stream().mapToLong(TermPurchase::getId).max().orElse(0L);
    }

    @Override
    public List<TermPurchase> findAll() {
        return new ArrayList<>(this.termPurchases);
    }

    @Override
    public List<TermPurchase> findByCustomerCode(String customerCode) {
        return this.termPurchases.stream().
                filter(tp -> tp.getCustomer().getCode().equals(customerCode)).
                collect(Collectors.toList());
    }

    @Override
    public synchronized <S extends TermPurchase> S save(S entity) {
        if (entity.getId() != null) {
            return entity;
        }
        entity.setId(nextId++);
        this.termPurchases.add(entity);
        return entity;
    }

    @Override
    public <S extends TermPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    @Override
    public List<TermPurchase> findByTermAndCurrDateAndEndDateAfter(Term term, Date currDate, Date nextDate) {
        return this.termPurchases.stream().
                filter(fp -> fp.getTerm().equals(term) && dayEqual(fp.getCurrDate(), currDate)
                        && dayAfter(fp.getEndDate(), nextDate)).
                collect(Collectors.toList());
    }

    //<editor-fold desc="useless">

    @Override
    public Optional<TermPurchase> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TermPurchase> findAllById(Iterable<Long> longs) {
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
    public void delete(TermPurchase entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TermPurchase> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
