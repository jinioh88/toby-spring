import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
        UserDao dao2 = context.getBean("userDao",UserDao.class);

        User user = new User();
        user.setId("whiteship9");
        user.setName("백기선");
        user.setPasswor("married");

        dao.add(user);

        User user2 = dao.get(user.getId());

        assertThat(user2.getName(),is(user.getName()));
        assertThat(user2.getPasswor(),is(user.getPasswor()));
    }
    
}
