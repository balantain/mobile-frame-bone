package appiumDriver;

import appiumConfiguration.AddressConfigurator;
import appiumConfiguration.AppiumConfigurationReader;
import appiumConfiguration.CapabilitiesConfigurator;
import appiumConfiguration.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static java.lang.String.format;

public class DriverManager {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE =
            EnvironmentType.valueOf(AppiumConfigurationReader.get().env().toUpperCase());
    private static AppiumDriver<MobileElement> appiumDriver;

    private DriverManager() {

    }

    public static AppiumDriver<MobileElement> getAppiumDriver() {
        if (appiumDriver == null) {
            appiumDriver = createDrive();
        }
        return appiumDriver;
    }

    private static AppiumDriver<MobileElement> createDrive() {
        switch (ENVIRONMENT_TYPE) {
            case LOCAL:
                appiumDriver = new AndroidDriver<MobileElement>
                        (AddressConfigurator.getAppiumDriverLocalService(AppiumConfigurationReader.get().appiumPort()),
                                CapabilitiesConfigurator.getLocalCapabilities());
                break;
            default:
                throw new IllegalArgumentException(format("Unexpected environment value: %s", ENVIRONMENT_TYPE));
        }
        LOG.info("Driver is created");
        LOG.info("Environment type is {}", ENVIRONMENT_TYPE);
        return appiumDriver;
    }

    public static void closeAppiumDriver() {
        getAppiumDriver().quit();
        appiumDriver = null;
        LOG.info("Driver is closed");
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", AppiumConfigurationReader.get().udid()));
            LOG.info("Emulator AVD is closed");
        } catch (IOException e) {
            LOG.info("Emulator AVD was not closed, message: {}", e.getMessage());
        }
    }
}
