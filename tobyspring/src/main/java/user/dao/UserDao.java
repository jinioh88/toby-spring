package user.dao;

import sun.tools.tree.ConditionalExpression;
import user.domain.User;

import java.sql.*;

public abstract class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
       Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2, user.getName());
        ps.setString(3,user.getPasswor());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
        ps.setString(1,id);

        ResultSet rs  = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPasswor(rs.getString("password"));

        rs.close();
        ps.close();
        con.close();

        return user;
    }

    public abstract Connection getConnection()  throws ClassNotFoundException, SQLException;

}

public class NUserDao extends UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {

    }
}

public class DUserDao extends UserDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException {

    }
}
