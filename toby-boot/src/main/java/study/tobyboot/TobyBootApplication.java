package study.tobyboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import study.tobyboot.user.dao.UserDAO;
import study.tobyboot.user.domain.User;

import java.sql.SQLException;

//@SpringBootApplication
public class TobyBootApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        SpringApplication.run(TobyBootApplication.class, args);
        UserDAO dao = new UserDAO();

        User user = new User();
        user.setId("jinioh88");
        user.setName("오세진");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getId() + " 조회 성공!");
    }

}
