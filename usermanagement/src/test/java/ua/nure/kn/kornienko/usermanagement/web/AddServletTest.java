package ua.nure.kn.kornienko.usermanagement.web;

import ua.nure.kn.kornienko.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {
    private final Date DATE = new Date();

    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {

        User user = new User("George", "Bush", DATE);

        User expectedUser = new User(1L, "George", "Bush", DATE);
        getMockUserDao().expectAndReturn("create", user, expectedUser);

        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("ok", "Ok");
        doPost();
    }

    public void testAddEmptyFirstName() {
        Date date = new Date();
        User user = new User(1000L, "Jon", "Doue", date);
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }

    public void testAddEmptyLastName() {
        Date date = new Date();
        User user = new User(1000L, "Jon", "Doue", date);
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }

    public void testAddIncorrectDateOfBirth() {
        Date date = new Date();
        User user = new User(1000L, "Jon", "Doue", date);
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("dateOfBirth", "bla-bla-bla");
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }
}