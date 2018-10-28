package user.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import java.util.List;

public class UserService {
    UserDao userDao;
    public static final int MIN_LOGCOUNT_FOR_SIVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradedLevels() {
        List<User> users = userDao.getAll();
        for(User user:users) {
            if(canUpgradeLevel(user)) {
                upgradedLevel(user);
            }
        }
    }

    private void upgradedLevel(User user) {
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
