package cloud.nativ.jakarta;

import lombok.extern.java.Log;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

@Log
public class ApplicationPropertiesReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(ApplicationPropertiesReader.class.getClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Error occurred while trying to read the application properties.", e);
        }
    }

    public static String getValue(final String key) {
        return PROPERTIES.getProperty(key);
    }
}
