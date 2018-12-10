package ua.nure.kn.kornienko.usermanagement.db;

import ua.nure.kn.kornienko.usermanagement.User;

import java.util.Collection;

public interface UserDao {

    User create(User user) throws DatabaseException;

    void update(User user) throws DatabaseException;

    void delete(User user) throws DatabaseException;

    User find(Long ID) throws DatabaseException;

    Collection <User> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);

}
