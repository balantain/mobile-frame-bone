package appiumConfiguration;

import org.openqa.selenium.remote.DesiredCapabilities;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.AVD;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator() {
    }

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UDID, AppiumConfigurationReader.get().udid());
        capabilities.setCapability(AVD, AppiumConfigurationReader.get().localDeviceName());
        capabilities.setCapability(PLATFORM_NAME, AppiumConfigurationReader.get().platformName());
        capabilities.setCapability(PLATFORM_VERSION, AppiumConfigurationReader.get().platformVersion());
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(BROWSER_NAME, AppiumConfigurationReader.get().browserName());
        return capabilities;
    }
}
