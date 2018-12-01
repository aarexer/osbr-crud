package aarexer.application.repository.site;

import aarexer.application.model.site.User;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends LongKeyedRepository<User> {
    public List<User> findUsersByFirstName(String firstName);
}
