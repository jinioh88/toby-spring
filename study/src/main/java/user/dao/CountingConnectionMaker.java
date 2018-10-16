package user.dao;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
    int counter = 0;
    private DataSource dataSource;

    public CountingConnectionMaker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return dataSource.getConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
