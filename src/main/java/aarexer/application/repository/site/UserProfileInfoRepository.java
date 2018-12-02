package aarexer.application.repository.site;

import aarexer.application.model.site.UserProfileInfo;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserProfileInfoRepository extends LongKeyedRepository<UserProfileInfo> {
    List<UserProfileInfo> findUserProfileInfosByCity(String city);
}
