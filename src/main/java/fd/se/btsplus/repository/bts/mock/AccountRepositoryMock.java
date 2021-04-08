package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Profile("!prod")
@Component
@AllArgsConstructor
public class AccountRepositoryMock implements AccountRepository {
    private static final String path = "json/bts/accounts.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<Account> accounts;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.accounts = new HashSet<>(jsonUtils.readList(jsonStr, Account.class));
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public Account findByAccountNumAndPassword(String accountNum, String password) {
        return accounts.stream().filter(ac -> {
            final String acNum = ac.getAccountNum();
            final String acPass = ac.getPassword();
            return acNum != null && acPass != null && acNum.equals(accountNum) && acPass.equals(password);
        }).findFirst().orElse(null);
    }

    @Override
    public List<Account> findByCustomer(Customer customer) {
        return findByCustomerCode(customer.getCode());
    }

    @Override
    public List<Account> findByCustomerCode(String customerCode) {
        return this.accounts.stream().
                filter(ac -> ac.getCustomer().getCode().equals(customerCode)).
                collect(Collectors.toList());
    }

    @Override
    public <S extends Account> S save(S entity) {
        this.accounts.remove(entity);
        this.accounts.add(entity);
        return entity;
    }

    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    //<editor-fold desc="useless">

    @Override
    public Optional<Account> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Account> findAllById(Iterable<Long> longs) {
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
    public void delete(Account entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
