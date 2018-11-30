package aarexer.application.repository.site;

import aarexer.application.ApplicationTest;
import aarexer.application.model.site.Gender;
import aarexer.application.model.site.UserProfileInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class UserProfileInfoRepositoryTest extends ApplicationTest {
    @Autowired
    private UserProfileInfoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void beforeEach() {
        repository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void saveUserProfileInfoTest() {
        Assert.assertEquals(0, repository.findAll().size());
        Assert.assertEquals(0, userRepository.findAll().size());

        UserProfileInfo userProfileInfo = new UserProfileInfo("98518872", Gender.MALE, LocalDate.now(), "msk", "ru", "ru");

        repository.save(userProfileInfo);

        Assert.assertEquals(1, repository.findAll().size());
        Assert.assertEquals(0, userRepository.findAll().size());

        Optional<UserProfileInfo> persisted = repository.getById(1L);
        Assert.assertTrue(persisted.isPresent());
        Assert.assertEquals(userProfileInfo, persisted.get());
    }
}
