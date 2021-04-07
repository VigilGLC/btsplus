package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Transaction;
import fd.se.btsplus.repository.bts.TransactionRepository;
import fd.se.btsplus.utils.JSONUtils;
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
public class TransactionRepositoryMock implements TransactionRepository {
    private final ResourceUtils resourceUtils;
    private final JSONUtils jsonUtils;
    private static final String path = "json/bts/transactions.json";
    private Set<Transaction> transactions;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.transactions = new HashSet<>(jsonUtils.readList(jsonStr, Transaction.class));
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(this.transactions);
    }

    //<editor-fold desc="useless">
    @Override
    public <S extends Transaction> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Transaction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Transaction> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }



    @Override
    public Iterable<Transaction> findAllById(Iterable<Long> longs) {
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
    public void delete(Transaction entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Transaction> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
