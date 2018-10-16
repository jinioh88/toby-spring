package user;

import org.springframework.context.support.GenericXmlApplicationContext;
import user.dao.UserDao;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);
        UserDao dao2 = context.getBean("userDao",UserDao.class);

        System.out.println(dao);
        System.out.println(dao2);
    }
}
