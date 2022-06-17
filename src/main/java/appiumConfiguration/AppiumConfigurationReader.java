package appiumConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class AppiumConfigurationReader {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final Properties properties = new Properties();

    private static AppiumConfigurationReader instance;

    private AppiumConfigurationReader() {
    }

    public static AppiumConfigurationReader get() {
        if (instance == null) {
            instance = new AppiumConfigurationReader();
            try {
                properties.load(new FileInputStream("src/main/resources/appiumTest.properties"));
            } catch (Exception e) {
                LOG.error("Properties were not loaded");
            }
        }
        return instance;
    }

    public String env() {
        return properties.getProperty("env.type");
    }

    public String platformName() {
        return properties.getProperty("platform.name");
    }

    public String platformVersion() {
        return properties.getProperty("platform.version");
    }

    public String localDeviceName() {
        return properties.getProperty("local.device.name");
    }

    public String udid() {
        return properties.getProperty("udid");
    }

    public String appiumAddress() {
        return properties.getProperty("appium.address");
    }

    public int appiumPort() {
        return Integer.parseInt(properties.getProperty("appium.port"));
    }

    public String browserName() {
        return properties.getProperty("browser.name");
    }
}
