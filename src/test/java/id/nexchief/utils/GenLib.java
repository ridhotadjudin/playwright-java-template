package id.nexchief.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GenLib {
    private static final Logger log = Logger.getLogger(GenLib.class);
    private String systemDir = System.getProperty("user.dir");

    public Properties getPropertiesFromFile(String property_filename) throws IOException {
        Properties prop = loadPropertiesFromFile(property_filename);
        adjustSystemProperties(prop);
        log.info("Succeed get properties from file");
        return prop;
    }

    private Properties loadPropertiesFromFile(String property_filename) throws IOException {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(systemDir + "\\src\\test\\resources\\properties\\" + property_filename);
            log.info("Success load property file" + property_filename);
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Property file '" + property_filename + "' not found in the classpath");
        }
        return prop;
    }

    private void adjustSystemProperties(Properties prop) {
        for (Object propKey : prop.keySet()) {
            String key = String.valueOf(propKey);
            if (StringUtils.isNotEmpty(System.getProperty(key))) {
                prop.setProperty(key, System.getProperty((key)));
            } else {
                log.debug("Cannot found the property value");
            }
        }
    }
}
