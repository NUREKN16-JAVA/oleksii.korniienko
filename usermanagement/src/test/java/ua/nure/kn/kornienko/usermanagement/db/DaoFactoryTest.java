package test.java.ua.nure.kn.kornienko.usermanagement.db;

import junit.framework.TestCase;
import main.java.ua.nure.kn.kornienko.usermanagement.db.DaoFactory;
import main.java.ua.nure.kn.kornienko.usermanagement.db.UserDao;

public class DaoFactoryTest extends TestCase {

    public void testGetUserDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        assertNotNull("DaoFactory is null",daoFactory);
        UserDao userDao = daoFactory.getUserDao();
        assertNotNull("UserDao is null",userDao);
    }
}