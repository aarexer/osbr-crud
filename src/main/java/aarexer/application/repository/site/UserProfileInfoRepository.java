package aarexer.application.repository.site;

import aarexer.application.model.site.UserProfileInfo;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileInfoRepository extends LongKeyedRepository<UserProfileInfo> {
}
