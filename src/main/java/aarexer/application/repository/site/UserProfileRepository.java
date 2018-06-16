package aarexer.application.repository.site;

import aarexer.application.model.site.UserProfile;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends LongKeyedRepository<UserProfile> {
}
