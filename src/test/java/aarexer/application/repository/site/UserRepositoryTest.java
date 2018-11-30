package aarexer.application.repository.site;

import aarexer.application.ApplicationTest;
import aarexer.application.model.site.Gender;
import aarexer.application.model.site.User;
import aarexer.application.model.site.UserProfileInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest extends ApplicationTest {
    @Autowired
    private UserProfileInfoRepository userProfileInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void beforeEach() {
        userProfileInfoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void saveUserWithProfileInfoTest() {
        Assert.assertEquals(0, userProfileInfoRepository.findAll().size());
        Assert.assertEquals(0, userRepository.findAll().size());

        UserProfileInfo userProfileInfo = new UserProfileInfo("98518872", Gender.MALE, LocalDate.now(), "msk", "ru", "ru");

        User user = new User("Aleksandr", "Kuchuk", "aaa", "pass");
        user.setUserProfileInfo(userProfileInfo);

        userRepository.save(user);

        // in each repository
        Assert.assertEquals(1, userProfileInfoRepository.findAll().size());
        Assert.assertEquals(1, userRepository.findAll().size());

        Optional<UserProfileInfo> persist = userProfileInfoRepository.getById(1L);
        Assert.assertTrue(persist.isPresent());

        Optional<User> persistUser = userRepository.getById(1L);
        Assert.assertTrue(persistUser.isPresent());

        User userFromDb = persistUser.get();
        Assert.assertEquals(userFromDb, user);
        Assert.assertEquals(userFromDb.getUserProfileInfo(), userProfileInfo);
    }

    @Test
    public void saveUsersWithSameFirstNameAndProfileInfoAndFindListOfThemByFirstName() {
        Assert.assertEquals(0, userProfileInfoRepository.findAll().size());
        Assert.assertEquals(0, userRepository.findAll().size());

        UserProfileInfo userProfileInfo = new UserProfileInfo("98518872", Gender.MALE, LocalDate.now(), "msk", "ru", "ru");

        User user = new User("Aleksandr", "Kuchuk", "aaa", "pass");
        user.setUserProfileInfo(userProfileInfo);

        userRepository.save(user);

        // in each repository
        Assert.assertEquals(1, userProfileInfoRepository.findAll().size());
        Assert.assertEquals(1, userRepository.findAll().size());

        UserProfileInfo userProfileInfo2 = new UserProfileInfo("9851882172", Gender.MALE, LocalDate.now(), "msk", "ru", "ru");

        User user2 = new User("Aleksandr", "Kuchuk2", "aaa2", "pass");
        user2.setUserProfileInfo(userProfileInfo2);

        userRepository.save(user2);

        // in each repository
        Assert.assertEquals(2, userProfileInfoRepository.findAll().size());
        Assert.assertEquals(2, userRepository.findAll().size());

        List<User> users = userRepository.findUsersByFirstName("Aleksandr");
        Assert.assertNotNull(users);
        Assert.assertEquals(2, users.size());

        User userFromDb1 = users.get(0);
        Assert.assertEquals(userFromDb1, user);
        Assert.assertEquals(userFromDb1.getUserProfileInfo(), userProfileInfo);

        User userFromDb2 = users.get(1);
        Assert.assertEquals(userFromDb2, user2);
        Assert.assertEquals(userFromDb2.getUserProfileInfo(), userProfileInfo2);
    }
}
