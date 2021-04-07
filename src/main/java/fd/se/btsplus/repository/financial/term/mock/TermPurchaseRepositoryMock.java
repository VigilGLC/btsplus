package fd.se.btsplus.repository.financial.term.mock;

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

@Profile("!prod")
@Component
@AllArgsConstructor
public class TermPurchaseRepositoryMock implements TermPurchaseRepository {
    private static final String path = "json/financial/term/termPurchase's.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<TermPurchase> termPurchases;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.termPurchases = new HashSet<>(jsonUtils.readList(jsonStr, TermPurchase.class));
    }

    @Override
    public List<TermPurchase> findAll() {
        return new ArrayList<>(this.termPurchases);
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends TermPurchase> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TermPurchase> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

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
