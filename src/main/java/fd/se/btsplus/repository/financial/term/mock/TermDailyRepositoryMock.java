package fd.se.btsplus.repository.financial.term.mock;

import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.model.entity.financial.term.TermDaily;
import fd.se.btsplus.repository.IRepositoryMock;
import fd.se.btsplus.repository.financial.term.TermDailyRepository;
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
public class TermDailyRepositoryMock implements TermDailyRepository, IRepositoryMock {
    private static final String PATH = "json/financial/term/termDaily's.json";
    @NonNull
    private final ResourceUtils resourceUtils;
    @NonNull
    private final JsonUtils jsonUtils;
    private Set<TermDaily> set;

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
        this.set = new HashSet<>(jsonUtils.readList(jsonStr, TermDaily.class));
    }

    @Override
    public List<TermDaily> findAll() {
        return new ArrayList<>(this.set);
    }

    @Override
    public TermDaily findByTermAndDate(Term term, Date date) {
        return this.set.stream().
                filter(td -> td.getTerm().equals(term) && dayEqual(td.getDate(), date)).
                findFirst().orElse(null);
    }

    @Override
    public List<TermDaily> findByDate(Date date) {
        return this.set.stream().
                filter(td -> dayEqual(td.getDate(), date)).
                collect(Collectors.toList());
    }

    @Override
    public synchronized <S extends TermDaily> S save(S entity) {
        if (entity.getId() != null) {
            return entity;
        }
        entity.setId(nextId++);
        this.set.add(entity);
        return entity;
    }

    @Override
    public <S extends TermDaily> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }
    //<editor-fold desc="useless">

    @Override
    public Optional<TermDaily> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TermDaily> findAllById(Iterable<Long> longs) {
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
    public void delete(TermDaily entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TermDaily> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
