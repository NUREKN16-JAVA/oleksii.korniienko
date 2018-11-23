package main.java.ua.nure.kn.kornienko.usermanagement.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private String driver = "org.hsqldb.jdbcDriver";
    private String url = "jdbc:hsqldb:file:db/usermanagement";
    private String user = "sa";
    private String password = "";

    @Override
    public Connection createConnection() throws DatabaseException {


        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException(e);
//            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

}
