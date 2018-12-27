package ua.nure.kn.kornienko.usermanagement.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A class that implements internationalization for user management application. Works with intern_text.properties file
 */
public class TextManager {
    private static final String BUNDLE_NAME = "intern_text";

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private TextManager() {

    }

    public static String getString(String key) {
        try {
            return BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!";
        }
    }
}
