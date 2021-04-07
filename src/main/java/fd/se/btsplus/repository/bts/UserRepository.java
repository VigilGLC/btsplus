package fd.se.btsplus.repository.bts;

import fd.se.btsplus.model.entity.bts.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);
}
