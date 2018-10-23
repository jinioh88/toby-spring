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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

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
            new User("gymee","park","p1", Level.BASIC,49,0),
            new User("joytouch","kimmyong","p2", Level.BASIC,50,0),
            new User("erwins","Shin","p3",Level.SILVER,60,29),
            new User("madnite1","Lee","p4",Level.SILVER,60,30),
            new User("green","Oh","p5",Level.GOLD,100,100)
        );
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for(User user:users) userDao.add(user);

        userService.upgradedLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel(), is(expectedLevel));
    }
}
