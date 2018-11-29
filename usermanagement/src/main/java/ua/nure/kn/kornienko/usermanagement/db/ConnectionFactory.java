package ua.nure.kn.kornienko.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {

    Connection createConnection() throws DatabaseException;
}
