package aarexer.application.repository.site;

import aarexer.application.ApplicationTest;
import aarexer.application.model.site.Gender;
import aarexer.application.model.site.UserProfileInfo;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void findUserProfileInfosByCityTest() {
        Assert.assertEquals(0, repository.findAll().size());
        Assert.assertEquals(0, userRepository.findAll().size());

        UserProfileInfo userProfileInfo = new UserProfileInfo("98518872", Gender.MALE, LocalDate.now(), "msk", "ru", "ru");
        UserProfileInfo userProfileInfo2 = new UserProfileInfo("985188722", Gender.MALE, LocalDate.now(), "msk", "ru", "ru");
        UserProfileInfo userProfileInfo3 = new UserProfileInfo("985188723", Gender.MALE, LocalDate.now(), "ekb", "ru", "ru");

        repository.save(userProfileInfo);
        repository.save(userProfileInfo2);
        repository.save(userProfileInfo3);

        Assert.assertEquals(3, repository.findAll().size());
        Assert.assertEquals(0, userRepository.findAll().size());

        List<UserProfileInfo> persisted = repository.findUserProfileInfosByCity("msk");
        Assert.assertNotNull(persisted);
        Assert.assertEquals(2, persisted.size());
        Assert.assertEquals(Lists.newArrayList(userProfileInfo, userProfileInfo2), persisted);
    }
}
