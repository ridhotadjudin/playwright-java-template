package id.nexchief.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class TestDataContext {
    private static Logger log = Logger.getLogger(TestDataContext.class);
    private static GenLib genLib = new GenLib();

    public static String getBrowserName() throws IOException {
        Properties app_properties = genLib.getPropertiesFromFile("application.properties");
        String browserTesting = app_properties.getProperty("browser");
        log.info("Browser Testing : " + browserTesting);
        return browserTesting;
    }

    public static boolean getHeadless() throws IOException {
        Properties app_properties = genLib.getPropertiesFromFile("application.properties");
        Boolean statusHeadless = Boolean.parseBoolean(app_properties.getProperty("headless"));
        log.info("Headless Status : " + statusHeadless);
        return statusHeadless;
    }

    public static boolean getRecordingVideo() throws IOException {
        Properties app_properties = genLib.getPropertiesFromFile("application.properties");
        Boolean statusRecording = Boolean.parseBoolean(app_properties.getProperty("recordingVideo"));
        log.info("Recording Video : " + statusRecording);
        return statusRecording;
    }

    public static String getTimeout() throws IOException {
        Properties app_properties = genLib.getPropertiesFromFile("application.properties");
        int getTimeout = Integer.parseInt(app_properties.getProperty("timeout")) * 1000;
        String timeout = Integer.toString(getTimeout);
        log.info("Timeout : " + timeout);
        return timeout;
    }

    public static String getEnvironmentProfile() throws IOException {
        Properties env_properties = genLib.getPropertiesFromFile("application.properties");
        String testingEnvironment = env_properties.getProperty("environment");
        log.info("Testing Environment : " + testingEnvironment);
        return testingEnvironment;
    }

    public static String getEnvironmentUrl(String environment) throws IOException {
        String finalUrl = "";
        Properties url_properties = genLib.getPropertiesFromFile("url.properties");
        finalUrl = url_properties.getProperty(environment);
        log.info("URL : " + finalUrl);
        return finalUrl;
    }

}
