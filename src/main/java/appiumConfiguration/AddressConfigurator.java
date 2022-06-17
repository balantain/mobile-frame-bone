package appiumConfiguration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class AddressConfigurator {

    private static final Logger LOG = LogManager.getRootLogger();
    private static AppiumDriverLocalService appiumDriverLocalService;
    private static final String KILL_NODE_COMMAND = "taskkill /F /IM node.exe";

    private AddressConfigurator() {
    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) startService(port);
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        makePortAvailableIfOccupied(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(AppiumConfigurationReader.get().appiumAddress())
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "error")
                .withArgument(() -> "--allow-insecure", "chromedriver_autodownload")
                .build();
        appiumDriverLocalService.start();
        LOG.info("Appium server was started on port {}", port);
    }

    public static void stopService() {
        appiumDriverLocalService.stop();
        LOG.info("Appium server stopped");
    }

    private static void makePortAvailableIfOccupied(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec(KILL_NODE_COMMAND);
            } catch (IOException e) {
                LOG.error("Couldn't make portal free, message: {}", e.getMessage());
            }
        }
    }

    public static boolean isPortFree(int port) {
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)) {
            LOG.info("Port - {} is available and ready to use", port);
        } catch (Exception e) {
            isFree = false;
            LOG.warn("Port - {} is occupied by some process, process will be terminated", port);
        }
        return isFree;
    }
}
