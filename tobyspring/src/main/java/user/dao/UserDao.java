package user.dao;

import user.domain.User;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/springbook?serverTimezone=UTC&useSSL=false";
        String dbUser = "spring";
        String password = "book";
        Connection con = DriverManager.getConnection(url, dbUser, password);
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook","root","");
        PreparedStatement ps = con.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1,user.getId());
        ps.setString(2, user.getName());
        ps.setString(3,user.getPasswor());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook","spring","");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/springbook?serverTimezone=UTC&useSSL=false";
        String dbUser = "spring";
        String password = "book";
        Connection con = DriverManager.getConnection(url, dbUser, password);
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
}
