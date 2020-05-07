package study.tobyboot.user.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import study.tobyboot.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException {
        class AddStatement implements StatementStrategy {
            @Override
            public PreparedStatement makePrepareStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        }

        StatementStrategy ps = new AddStatement();
        jdbcContextWithStatementsStrategy(ps);
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try(Connection c = dataSource.getConnection();) {
            ps = c.prepareStatement("select * from users where id = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();

            if(rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            if(user == null)
                throw new EmptyResultDataAccessException(1);

            return user;
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void deleteAll() throws SQLException {
        StatementStrategy strategy = new DeleteAllStatement();
        jdbcContextWithStatementsStrategy(strategy);
    }

    public int getCount() throws SQLException {
        ResultSet rs = null;
        try(Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("select count(*) from users")) {
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } finally {
            if(rs == null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void jdbcContextWithStatementsStrategy(StatementStrategy stmt) throws SQLException {
        PreparedStatement ps = null;
        try(Connection c = dataSource.getConnection();) {
            ps = stmt.makePrepareStatement(c);
            ps.executeUpdate();
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
        }
    }
}
