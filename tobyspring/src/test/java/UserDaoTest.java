import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;

public class UserDaoTest {
    @Test
    public void anddndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao",UserDao.class);
        User user1 = new User("gyumee","박상철","spring1");
        User user2 = new User("lee700","이길원","spring2");

        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.add(user1);dao.add(user2);
        assertThat(dao.getCount(),is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(),is(user1.getName()));
        assertThat(userget1.getPasswor(),is(user1.getPasswor()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(),is(user2.getName()));
        assertThat(userget2.getPasswor(),is(user2.getPasswor()));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao",UserDao.class);
        User user1 = new User("gyumee","박성철","spring1");
        User user2 = new User("leegw700","이길원","spring2");
        User user3 = new User("bumjin","박범진","spring3");

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
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);
        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.get("unkown_id");
    }

}
