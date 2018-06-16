package aarexer.application.repository.site;

import aarexer.application.model.site.User;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LongKeyedRepository<User>{
}
