package main.java.ua.nure.kn.kornienko.usermanagement.db;

import main.java.ua.nure.kn.kornienko.usermanagement.User;

import java.util.Collection;

public class HsqldbUserDao implements UserDao {
    private ConnectionFactory connectionFactory;

    public HsqldbUserDao(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        return null;
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
