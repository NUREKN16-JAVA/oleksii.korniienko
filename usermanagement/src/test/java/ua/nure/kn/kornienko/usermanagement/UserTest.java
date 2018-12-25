package ua.nure.kn.kornienko.usermanagement;

import junit.framework.TestCase;
import ua.nure.kn.kornienko.usermanagement.User;

import java.util.Calendar;

public class UserTest extends TestCase {

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
    private static final int ETALONE_AGE_EQ_DATE = 18;

    // test birth date the day after
    private static final int BIRTH_YEAR_AFTER_DAY = 1999;
    private static final int BIRTH_MONTH_AFTER_DAY = Calendar.NOVEMBER;
    private static final int BIRTH_DAY_AFTER_DAY = 23;
    private static final int ETALONE_AGE_AFTER_DAY = 18;

    // test birth date the month after
    private static final int BIRTH_YEAR_AFTER_MONTH = 1999;
    private static final int BIRTH_MONTH_AFTER_MONTH = Calendar.DECEMBER;
    private static final int BIRTH_DAY_AFTER_MONTH = 22;
    private static final int ETALONE_AGE_AFTER_MONTH = 18;

//    private static final int YEAR_OF_BIRTH = 1999;
//    private static final int MONTH_OF_BIRTH = 11;
//    private static final int DAY_OF_BIRTH = 22;


    // test 1 день рождения уже рошёл, но месяц ещё идёт
//    private static final int ETALONE_AGE_1 = 47;



    private User user;
//    @Before
    public void setUp() throws Exception {
        user = new User();
    }

//    @After
    public void tearDown() throws Exception {
    }

    //@test
    public void testGetFullName() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        assertEquals(FULL_NAME_ETALONE, user.getFullName());
    }

    //@test
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

    //@test
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

    //@test
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

    //@test
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

    //@test
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