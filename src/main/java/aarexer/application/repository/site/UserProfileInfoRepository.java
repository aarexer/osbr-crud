package aarexer.application.repository.site;

import aarexer.application.model.site.UserProfileInfo;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileInfoRepository extends LongKeyedRepository<UserProfileInfo> {
    List<UserProfileInfo> findUserProfileInfosByCity(String city);
}
