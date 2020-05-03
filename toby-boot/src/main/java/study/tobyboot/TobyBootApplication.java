package study.tobyboot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.tobyboot.user.dao.CountingConnectionMaker;
import study.tobyboot.user.dao.CountingDaoFactory;
import study.tobyboot.user.dao.DaoFactory;
import study.tobyboot.user.dao.UserDAO;
import study.tobyboot.user.domain.User;

import java.sql.SQLException;

public class TobyBootApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

//        CountingConnectionMaker cm = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        System.out.println("Connection counter : " + cm.getCounter());

        User user = new User();
        user.setId("jinioh5");
        user.setName("오세진");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getId() + " 조회 성공!");
    }

}
