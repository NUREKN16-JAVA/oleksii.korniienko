package main.java.ua.nure.kn.kornienko.usermanagement.db;

import main.java.ua.nure.kn.kornienko.usermanagement.User;


import java.sql.*;
import java.util.Collection;

public class HsqldbUserDao implements UserDao {
    private ConnectionFactory connectionFactory;
    private static final String INSERT_QUERU = "INSERT into users (firstname, lastname, dateOfBirth) values (?, ?, ?)";

    public HsqldbUserDao(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERU);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()) );
            int n = statement.executeUpdate();
            if(n != 1){
                throw new DatabaseException("Number of the date inserted rows: " + n);
            }
            CallableStatement callableStatement = connection
                    .prepareCall("call IDENTITY()");
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next()){
                user.setId(new Long(keys.getLong(1)));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } catch (DatabaseException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(long id) throws DatabaseException {
        return null;
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        return null;
    }

    @Override
    public void update(User user) throws DatabaseException {

    }

    @Override
    public void delete(User user) throws DatabaseException {

    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
