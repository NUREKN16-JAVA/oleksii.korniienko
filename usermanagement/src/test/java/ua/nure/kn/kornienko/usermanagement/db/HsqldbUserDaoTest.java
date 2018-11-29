package ua.nure.kn.kornienko.usermanagement.db;

import ua.nure.kn.kornienko.usermanagement.User;
import ua.nure.kn.kornienko.usermanagement.db.*;
import org.dbunit.DatabaseTestCase;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private static final long TEST_ID = 1001L;
    private static final String FIRST_NAME = "Alex";
    private static final String LAST_NAME = "Kornienko";

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

    public void setUp() throws Exception {
        super.setUp();
        connectionFactory = new ConnectionFactoryImpl();
        userDao = new HsqldbUserDao(connectionFactory);
    }

    public void tearDown() throws Exception {
        super.tearDown();

    }

    public void testCreateUser() throws DatabaseException {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
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

    public void testFind() {
        long id = TEST_ID;
        try {
            User user = userDao.find(id);

            assertNotNull(user);

            long userId = user.getId();
            assertEquals(id, userId);
        } catch (DatabaseException e) {
            fail(e.getMessage());
        }

    }

    public void testDelete() {
        User testUser = createUser();
        int expectedBeforeSize = 2;
        int expectedAfterSize = 1;
        try {
            int beforeSize = userDao.findAll().size();
            userDao.delete(testUser);
            int afterSize = userDao.findAll().size();

            assertEquals(expectedBeforeSize, beforeSize);
            assertEquals(expectedAfterSize, afterSize);
        } catch (DatabaseException e) {
            fail(e.getMessage());
        }
    }

    public void testUpdate() {
        User testUser = createUser();
        try {
            userDao.update(testUser);
            User updatedUser = userDao.find(TEST_ID);

            assertEquals(FIRST_NAME, updatedUser.getFirstName());
            assertEquals(LAST_NAME, updatedUser.getLastName());
        } catch (DatabaseException e) {
            fail(e.getMessage());
        }
    }

    private User createUser() {
        User user = new User();
        user.setId(TEST_ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setDateOfBirth(new Date());
        return user;
    }
}
