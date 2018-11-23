package test.java.ua.nure.kn.kornienko.usermanagement;

import main.java.ua.nure.kn.kornienko.usermanagement.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class UserTest {

    private static final String FIRST_NAME = "Ivan";
    private static final String LAST_NAME = "Ivanhoe";
    private static final String FULL_NAME_ETALONE = "Ivanhoe, Ivan";

    // test 22/11/18
    // test birth date the day before
    private static final int BIRTH_YEAR_BEFORE_DAY = 1999;
    private static final int BIRTH_MONTH_BEFORE_DAY = Calendar.NOVEMBER;
    private static final int BIRTH_DAY_BEFORE_DAY = 21;
    private static final int ETALONE_AGE_BEFORE_DAY = 18;

    // test birth date the month before
    private static final int BIRTH_YEAR_BEFORE_MONTH = 1999;
    private static final int BIRTH_MONTH_BEFORE_MONTH = Calendar.OCTOBER;
    private static final int BIRTH_DAY_BEFORE_MONTH = 22;
    private static final int ETALONE_AGE_BEFORE_MONTH = 18;

    // test birth date date eq
    private static final int BIRTH_YEAR_EQ_DATE = 1999;
    private static final int BIRTH_MONTH_EQ_DATE = Calendar.NOVEMBER;
    private static final int BIRTH_DAY_EQ_DATE = 23;
    private static final int ETALONE_AGE_EQ_DATE = 19;

    // test birth date the day after
    private static final int BIRTH_YEAR_AFTER_DAY = 1999;
    private static final int BIRTH_MONTH_AFTER_DAY = Calendar.NOVEMBER;
    private static final int BIRTH_DAY_AFTER_DAY = 23;
    private static final int ETALONE_AGE_AFTER_DAY = 19;

    // test birth date the month after
    private static final int BIRTH_YEAR_AFTER_MONTH = 1999;
    private static final int BIRTH_MONTH_AFTER_MONTH = Calendar.DECEMBER;
    private static final int BIRTH_DAY_AFTER_MONTH = 22;
    private static final int ETALONE_AGE_AFTER_MONTH = 19;

//    private static final int YEAR_OF_BIRTH = 1999;
//    private static final int MONTH_OF_BIRTH = 11;
//    private static final int DAY_OF_BIRTH = 22;


    // test 1 день рождения уже рошёл, но месяц ещё идёт
//    private static final int ETALONE_AGE_1 = 47;



    private User user;
    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetFullName() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        assertEquals(FULL_NAME_ETALONE, user.getFullName());
    }

    @Test
    // before day
    public void testGetAge1(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(
            BIRTH_YEAR_BEFORE_DAY,
            BIRTH_MONTH_BEFORE_DAY,
            BIRTH_DAY_BEFORE_DAY
        );

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_BEFORE_DAY, user.getAge());
    }

    @Test
    // before month
    public void testGetAge2(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(
                BIRTH_YEAR_BEFORE_MONTH,
                BIRTH_MONTH_BEFORE_MONTH,
                BIRTH_DAY_BEFORE_MONTH
        );

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_BEFORE_MONTH, user.getAge());
    }

    @Test
    // eq date
    public void testGetAge3(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(
                BIRTH_YEAR_EQ_DATE,
                BIRTH_MONTH_EQ_DATE,
                BIRTH_DAY_EQ_DATE
        );

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_EQ_DATE, user.getAge());
    }

    @Test
    // after day
    public void testGetAge4(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(
                BIRTH_YEAR_AFTER_DAY,
                BIRTH_MONTH_AFTER_DAY,
                BIRTH_DAY_AFTER_DAY
        );

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_AFTER_DAY, user.getAge());
    }

    @Test
    // after month
    public void testGetAge5(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(
                BIRTH_YEAR_AFTER_MONTH,
                BIRTH_MONTH_AFTER_MONTH,
                BIRTH_DAY_AFTER_MONTH
        );

        user.setDateOfBirth(calendar.getTime());
        assertEquals(ETALONE_AGE_AFTER_MONTH, user.getAge());
    }

}