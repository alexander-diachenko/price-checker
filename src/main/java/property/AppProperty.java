package property;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class AppProperty {

    private final static Logger logger = Logger.getLogger(AppProperty.class);

    public static Properties getProperty() {
        Properties mainProperties = new Properties();
        FileInputStream file;
        String path = "./main.properties";
        try {
            file = new FileInputStream(path);
        mainProperties.load(file);
        file.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return mainProperties;
    }
}
