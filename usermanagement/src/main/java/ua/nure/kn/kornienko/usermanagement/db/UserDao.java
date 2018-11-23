package main.java.ua.nure.kn.kornienko.usermanagement.db;

import main.java.ua.nure.kn.kornienko.usermanagement.User;

import java.util.Collection;

public interface UserDao {
    /**
     * add new record to db with user
     * @param user with null id
     * @return modified user with auto generated id from DB
     *
     */
    User create(User user) throws DatabaseException;

    /**
     *
     * @param id
     * @return
     * @throws DatabaseException
     */
    User find(long id) throws DatabaseException;

    /**
     *
     * @return
     * @throws DatabaseException
     */
    Collection<User> findAll() throws DatabaseException;

    /**
     * all field
     * @param user
     * @throws DatabaseException
     */
    void update (User user) throws DatabaseException;

    /**
     *
     * @param user
     * @throws DatabaseException
     */
    void delete (User user) throws DatabaseException;

    /**
     *
     * @param connectionFactory
     */
    void setConnectionFactory(ConnectionFactory connectionFactory);

}
