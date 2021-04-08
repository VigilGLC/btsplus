package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.repository.bts.UserRepository;
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
public class UserRepositoryMock implements UserRepository {
    private static final String path = "json/bts/users.json";
    private final ResourceUtils resourceUtils;
    private final JsonUtils jsonUtils;
    private Set<User> users;

    @SneakyThrows
    @PostConstruct
    private void init() {
        final String jsonStr = resourceUtils.readFileAsString(path);
        this.users = new HashSet<>(jsonUtils.readList(jsonStr, User.class));
    }


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.users.stream().
                filter(usr -> usr.getUsername().equals(username) && usr.getPassword().equals(password)).
                findFirst().orElse(null);
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users);
    }

    //<editor-fold desc="useless">

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
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
    public void delete(User entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }
    //</editor-fold>
}
