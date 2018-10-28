package user.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static user.service.UserService.MIN_LOGCOUNT_FOR_SIVER;
import static user.service.UserService.MIN_RECCOMEND_FOR_GOLD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
            new User("gymee","park","p1", Level.BASIC,MIN_LOGCOUNT_FOR_SIVER-1,0),
            new User("joytouch","kimmyong","p2", Level.BASIC,MIN_LOGCOUNT_FOR_SIVER,0),
            new User("erwins","Shin","p3",Level.SILVER,60,MIN_RECCOMEND_FOR_GOLD-1),
            new User("madnite1","Lee","p4",Level.SILVER,60,MIN_RECCOMEND_FOR_GOLD),
            new User("green","Oh","p5",Level.GOLD,100,Integer.MAX_VALUE)
        );
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for(User user:users) userDao.add(user);

        userService.upgradedLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
    }

    @Test
    public void upgradeAllOrNothing() {
        UserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(this.userDao);
        userDao.deleteAll();
        for(User user:users) userDao.add(user);

        try{
            testUserService.upgradedLevels();
            fail("TestUserServiceException expected");
        }catch (TestUserService.TestUserServiceException e) {
        }
        checkLevelUpgraded(users.get(1), false);
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if(upgraded) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        } else{
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }
}
