package ua.nure.kn.kornienko.usermanagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = -3760492779402022862L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return new StringBuilder( getLastName())
            .append(", ")
            .append(getFirstName())
            .toString();
    }

    public int getAge() {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(new Date());

        Calendar calendarBirth = Calendar.getInstance();
        calendarBirth.setTime(getDateOfBirth());

        int age = calendarCurrent.get(Calendar.YEAR) - calendarBirth.get(Calendar.YEAR);

        if ((calendarCurrent.get( Calendar.MONTH) == calendarBirth.get( Calendar.MONTH)) &&
                (calendarCurrent.get( Calendar.DAY_OF_MONTH) > calendarBirth.get( Calendar.DAY_OF_MONTH))) {
            age--;
        }

        if(calendarCurrent.get( Calendar.MONTH) > calendarBirth.get( Calendar.MONTH))
            age--;

        return age;
    }
}