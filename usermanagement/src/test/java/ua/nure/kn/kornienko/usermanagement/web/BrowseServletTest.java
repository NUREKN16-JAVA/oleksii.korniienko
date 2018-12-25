package ua.nure.kn.kornienko.usermanagement.web;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ua.nure.kn.kornienko.usermanagement.User;

public class BrowseServletTest extends MockServletTestCase {

    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    public void testBrowse() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }

    public void testEdit() {
        User expectedUser = new User(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, expectedUser);
        addRequestParameter("edit", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User returnedUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull(returnedUser);
        assertSame(expectedUser, returnedUser);
    }

    public void testDelete() {
        User user = new User(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("id", String.valueOf(1000L));
        addRequestParameter("delete", "Delete");
        doPost();
        User returnedUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull(returnedUser);
        assertSame(user, returnedUser);
    }

    public void testDetails() {
        User user = new User(1000L, "John", "Doe", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("id", String.valueOf(1000L));
        addRequestParameter("details", "Details");
        doPost();
        User returnedUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull(returnedUser);
        assertSame(user, returnedUser);
    }
}

