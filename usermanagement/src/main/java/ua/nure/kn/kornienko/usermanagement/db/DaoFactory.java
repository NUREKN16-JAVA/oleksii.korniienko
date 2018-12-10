package ua.nure.kn.kornienko.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

    protected static final String USER_DAO = "dao.ua.nure.kornienko.usermanagement.db.UserDao";

    private static final String PROPERTIES_FILE = "settings.properties";
    private static final String DAO_FACTORY = "dao.factory";

    private static DaoFactory instance;
    protected static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class
                .getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DaoFactory() {

    }

    public static synchronized DaoFactory getInstance() {
        if(instance == null){
            Class<?> factoryClass;
            try {
                factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    public abstract UserDao getUserDao();

    protected ConnectionFactory getConnectionFactory() {

        return new ConnectionFactoryImpl(properties);
    }

    public static void init(Properties p){
        properties  = p;
        instance = null;
    }
}
