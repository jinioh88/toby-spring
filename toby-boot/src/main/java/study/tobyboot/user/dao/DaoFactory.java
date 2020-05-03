package study.tobyboot.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
//    @Bean
//    public UserDAO userDAO() {
//        return new UserDAO(connectionMaker());
//    }

    @Bean
    public UserDAO userDAO() {
        UserDAO userDAO = new UserDAO();
        userDAO.setDataSource(dataSource());
        return userDAO;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/toby");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }
}
