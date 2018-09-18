package user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.dao.CountingConnectionMaker;
import user.dao.CountingDaoFactory;
import user.dao.UserDao;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);

        CountingConnectionMaker ccm = context.getBean("connectionMaker",CountingConnectionMaker.class);
        System.out.println(ccm.getCounter());
    }
}
