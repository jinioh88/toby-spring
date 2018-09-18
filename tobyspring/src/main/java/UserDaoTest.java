import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

//        UserDao dao = context.getBean("userDao",UserDao.class);
//        UserDao dao2 = context.getBean("userDao",UserDao.class);
//
//        User user = new User();
//        user.setId("whiteship8");
//        user.setName("백기선");
//        user.setPasswor("married");
//
//        dao.add(user);
//
//        System.out.println(user.getId()+ " 등록 성공");
//
//        User user2 = dao.get(user.getId());
//
//        if(!user.getName().equals(user2.getName())) {
//            System.out.println("테스트 실패(name)");
//        }
//        else if(!user.getPasswor().equals(user2.getPasswor())) {
//            System.out.println("테스트 실패(password)");
//        }
//        else {
//            System.out.println("조회 테스트 성공!!");
//        }

    }
}
