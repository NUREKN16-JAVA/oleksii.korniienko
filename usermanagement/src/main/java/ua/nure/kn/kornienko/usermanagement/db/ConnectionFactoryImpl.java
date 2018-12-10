package ua.nure.kn.kornienko.usermanagement.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {

//    private String DRIVER = "org.hsqldb.jdbcDriver";
//    private String URL = "jdbc:hsqldb:file:db/usermanagement";
//    private String USER = "sa";
//    private String PASSWORD = "";

    private static final String CONNECTION_DRIVER = "connection.driver";
    private static final String CONNECTION_URL = "connection.url";
    private static final String CONNECTION_USER = "connection.user";
    private static final String CONNECTION_PASSWORD = "connection.password";

    private String driver;
    private String url;
    private String user;
    private String password;


//    public ConnectionFactoryImpl() {
//        this.driver = DRIVER;
//        this.url = URL;
//        this.user = USER;
//        this.password = PASSWORD;
//    }

    public ConnectionFactoryImpl(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public ConnectionFactoryImpl(Properties properties) {
        driver = properties.getProperty(CONNECTION_DRIVER);
        url = properties.getProperty(CONNECTION_URL);
        user = properties.getProperty(CONNECTION_USER);
        password = properties.getProperty(CONNECTION_PASSWORD);
    }


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
