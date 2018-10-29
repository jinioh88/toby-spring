package user.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao userDao;
    private PlatformTransactionManager transactionManager;

    public static final int MIN_LOGCOUNT_FOR_SIVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradedLevels() throws SQLException {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{
            List<User> users = userDao.getAll();
            for(User user:users) {
                if(canUpgradeLevel(user)) {
                    upgradedLevel(user);
                }
            }
            this.transactionManager.commit(status);
        }catch(Exception e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }

    protected void upgradedLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }

    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC: return (user.getLogin()>=MIN_LOGCOUNT_FOR_SIVER);
            case SILVER: return (user.getRecommand()>=MIN_RECCOMEND_FOR_GOLD);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unkown Level: "+currentLevel);
        }
    }

    public void add(User user) {
        if(user.getLevel()==null)
            user.setLevel(Level.BASIC);
        userDao.add(user);
    }
}
