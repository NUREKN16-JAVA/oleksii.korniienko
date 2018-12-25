package ua.nure.kn.kornienko.usermanagement.web;

import ua.nure.kn.kornienko.usermanagement.User;

import java.util.Date;

public class DeleteServletTest extends MockServletTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DeleteServlet.class);

    }

    public void testDelete() {
        User user = new User(new Long(1000), "John", "Doe", new Date());
        getMockUserDao().expect("delete", user);
        setSessionAttribute("user", user);
        addRequestParameter("ok", "Ok");
        doPost();
    }
}