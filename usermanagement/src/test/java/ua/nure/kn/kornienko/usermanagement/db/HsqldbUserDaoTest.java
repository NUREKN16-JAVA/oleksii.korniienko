package test.java.ua.nure.kn.kornienko.usermanagement.db;

import main.java.ua.nure.kn.kornienko.usermanagement.User;
import main.java.ua.nure.kn.kornienko.usermanagement.db.DatabaseException;
import main.java.ua.nure.kn.kornienko.usermanagement.db.UserDao;
import org.dbunit.DatabaseTestCase;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;

import java.util.Date;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private UserDao userDao;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        return null;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return null;
    }

    @Before
    public void setUp() throws Exception {
       super.setUp();
    }

    @After
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
        assertEquals(userExpected.getLastName(), user.getFullName());
        assertEquals(userExpected.getDateOfBirth(), user.getDateOfBirth());
    }
}
