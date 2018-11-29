package test.java.ua.nure.kn.kornienko.usermanagement.db;

import main.java.ua.nure.kn.kornienko.usermanagement.User;
import main.java.ua.nure.kn.kornienko.usermanagement.db.*;
//import org.dbunit.DBTestCase;
import org.dbunit.DatabaseTestCase;

//import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
//import org.junit.After;
//import org.junit.Before;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private UserDao userDao;
    private ConnectionFactory connectionFactory;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl();
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(
                getClass()
                .getClassLoader()
                .getResourceAsStream("usersDataSet.xml")
        );
        return dataSet;
    }

//    @Before
    public void setUp() throws Exception {
        super.setUp();
//        userDao = new HsqldbUserDao(new ConnectionFactoryImpl());
        connectionFactory = new ConnectionFactoryImpl();
        userDao = new HsqldbUserDao(connectionFactory);
    }


//    @After
    public void tearDown() throws Exception {
        super.tearDown();

    }

    public void testCreateUser() throws DatabaseException {
        User user = new User();
        user.setFirstName("Alex");
        user.setLastName("Kornienko");
        user.setDateOfBirth(new Date());

        assertNull(user.getId());

        User userExpected = userDao.create(user);
        assertNotNull(userExpected);
        assertNotNull(userExpected.getId());
        assertEquals(userExpected.getFirstName(), user.getFirstName());
        assertEquals(userExpected.getLastName(), user.getLastName());
        assertEquals(userExpected.getDateOfBirth(), user.getDateOfBirth());
    }

    public void testFindAll() throws DatabaseException {
        int expectedUsersNumber = 2;
        Collection<User> users = userDao.findAll();
        assertNotNull("Collection is null", users);
        assertEquals("Collection size.", expectedUsersNumber, users.size());
    }
}
