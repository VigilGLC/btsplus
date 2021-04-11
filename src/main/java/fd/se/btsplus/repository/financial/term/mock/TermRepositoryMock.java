package fd.se.btsplus.repository.financial.term.mock;

import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.repository.financial.term.TermRepository;
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
public class TermRepositoryMock implements TermRepository {
    private static final String path = "json/financial/term/terms.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<Term> stocks;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.stocks = new HashSet<>(jsonUtils.readList(jsonStr, Term.class));
    }

    @Override
    public List<Term> findAll() {
        return new ArrayList<>(this.stocks);
    }

    @Override
    public Term findById(long id) {
        return this.stocks.stream().filter(s -> Long.valueOf(id).equals(s.getId())).
                findFirst().orElse(null);
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends Term> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Term> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Term> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Term> findAllById(Iterable<Long> longs) {
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
    public void delete(Term entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Term> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
