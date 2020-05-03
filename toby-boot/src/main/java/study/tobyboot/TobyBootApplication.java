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

    }

}
