package user.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao userDao;
    private PlatformTransactionManager transactionManager;
    private MailSender mailSender;

    public static final int MIN_LOGCOUNT_FOR_SIVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradedLevels() throws SQLException {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            upgradeLevelsInternal();
            this.transactionManager.commit(status);
        }catch(Exception e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }

    private void upgradeLevelsInternal() {
        List<User> users = userDao.getAll();
        for(User user:users) {
            if(canUpgradeLevel(user)) {
                upgradedLevel(user);
            }
        }
    }

    protected void upgradedLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
        sendUpgradeEmail(user);
    }

    private void sendUpgradeEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 "+ user.getLevel().name()+"로 업그레이드 됨!");
        this.mailSender.send(mailMessage);
    }
// 여기서 다음으로 감ㅕㄴ
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
