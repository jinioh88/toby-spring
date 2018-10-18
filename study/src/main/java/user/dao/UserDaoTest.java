package user.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);

        User user1 = new User("gymee","park","spring1");
        User user2 = new User("leegw700","lee","spring2");

        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(),is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getId(),is(user1.getId()));
        assertThat(userget1.getName(),is(user1.getName()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getId(),is(user2.getId()));
        assertThat(userget2.getName(),is(user2.getName()));
    }

    @Test
    public void count() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao",UserDao.class);
        User user1 = new User("gymee","park","spring1");
        User user2 = new User("leegw700","lee","spring2");
        User user3 = new User("bi,kom","jin","spring3");

        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.add(user1);
        assertThat(dao.getCount(),is(1));

        dao.add(user2);
        assertThat(dao.getCount(),is(2));

        dao.add(user3);
        assertThat(dao.getCount(),is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao",UserDao.class);
        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.get("unkown_id");
    }
}
