package ua.nure.kn.kornienko.usermanagement.db;

import ua.nure.kn.kornienko.usermanagement.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

class HsqldbUserDao implements UserDao {
    private ConnectionFactory connectionFactory;

    private static final String INSERT_QUERU = "INSERT into users (firstname, lastname, dateOfBirth) values (?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
    private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ?  WHERE id = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_USERS_BY_FULL_NAME = "SELECT * FROM users WHERE FIRSTNAME = ? AND LASTNAME = ?";

    public HsqldbUserDao() {
    }

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
    public User find(Long id) throws DatabaseException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionFactory.createConnection()) {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.close();
                User resultUser = mapUser(resultSet);
                resultSet.close();
                return resultUser;
            }
            throw new DatabaseException("Can not find user by id: " + id);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        Statement statement = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionFactory.createConnection()) {
            Collection<User> users = new LinkedList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
            statement.close();
            resultSet.close();
            return users;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Collection<User> find(String firstName, String lastName) {
        Collection<User> userList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_FULL_NAME);
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(mapUser(resultSet));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return userList;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userList;
    }

    @Override
    public void update(User user) throws DatabaseException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionFactory.createConnection()) {
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            int count = 1;
            preparedStatement.setString(count++, user.getFirstName());
            preparedStatement.setString(count++, user.getLastName());
            preparedStatement.setDate(count++, new Date(user.getDateOfBirth().getTime()));
            preparedStatement.setLong(count, user.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows != 1) {
                throw new DatabaseException("Exception while update operation. Effected rows: " + updatedRows);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void delete(User user) throws DatabaseException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionFactory.createConnection()) {
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, user.getId());
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows != 1) {
                throw new DatabaseException("Exception while delete operation. Effected rows: " + updatedRows);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        Date dateOfBirth = resultSet.getDate(4);
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        return user;
    }
}
