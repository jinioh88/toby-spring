package com.example.toby;

import com.example.toby.user.dao.UserDao;
import com.example.toby.user.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TobyApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        SpringApplication.run(TobyApplication.class, args);
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPasswor("married");

        dao.add(user);

        System.out.println(user.getId()+ " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPasswor());

        System.out.println(user2.getId()+" 조회 성공!");

    }
}
