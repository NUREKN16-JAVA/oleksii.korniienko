package ua.nure.kn.kornienko.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 438128579530615585L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public User() {

    }
    public User(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;


    }
    public User(String firstName, String lastName, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = date;

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.isNull(id) ? 0 : Objects.hash(id);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}