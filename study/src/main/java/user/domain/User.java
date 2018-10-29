package user.domain;

public class User {
    String id;
    String name;
    String password;
    Level level;
    int login;
    int recommand;
    String email;

    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if(nextLevel==null) {
            throw new IllegalArgumentException(this.level+"은 업그레이드가 불가");
        } else {
            this.level = nextLevel;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRecommand() {
        return recommand;
    }

    public void setRecommand(int recommand) {
        this.recommand = recommand;
    }

    public User() {
    }

    public User(String id, String name, String password, Level level, int login, int recommand, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommand = recommand;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
