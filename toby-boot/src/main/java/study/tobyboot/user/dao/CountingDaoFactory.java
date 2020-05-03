package study.tobyboot.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
//    @Bean
//    public UserDAO userDAO() {
//        return new UserDAO(connectionMaker());
//    }

    @Bean
    public UserDAO userDAO() {
        UserDAO userDAO = new UserDAO();
//        userDAO.setDataSource(data());
        return userDAO;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
