package managers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OurDriverManager {

    public static final String SERVER_NAME = "//android.widget.CheckedTextView[contains(@text, '<serverName>')]";
    public static final String VENUE_NAME = "//android.widget.TextView[contains(@text, '<venueName>')]";
    public static final String DEVICE_NAME = "//android.widget.TextView[contains(@text, '<deviceName>')]";

    public static final String hostName = "https://stage.tray.com/";
    public static final String kdsUsername = "qa_automation_kds_login_2@gmail.com";
    public static final String kdsPassword = "KDSLogin#2";
    public static final String venueName = "Reports Validation - Do Not Use or Delete.";
    public static final String kdsDevice = "POS4";

    public static AndroidDriver<MobileElement> driver;

    @BeforeSuite
    public void setupAppium() throws MalformedURLException {
        final String urlRemote = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(urlRemote);

        if (System.getenv("DEVICEFARM_DEVICE_NAME") == null) setUpAppiumOnLocal(url);
        else setUpAppiumOnDeviceFarm(url);
    }

    private void setUpAppiumOnDeviceFarm(URL url) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        driver = new AndroidDriver<MobileElement>(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void setUpAppiumOnLocal(URL url) {
        String lnkKDS = System.getProperty("user.dir") + "/src/test/resources/KDS.apk";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("udid", "HA0KFKBC");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("appWaitDuration", "5000");
        caps.setCapability("appWaitPackage", "com.vendsy.tray.pos");
        caps.setCapability("appWaitActivity", "com.vendsy.tray.pos.InitActivity");
        caps.setCapability("app", lnkKDS);
        caps.setCapability(MobileCapabilityType.FULL_RESET, "true");
        driver = new AndroidDriver(url, caps);

    }

    @AfterSuite
    public void tearDownAppium() {
        driver.quit();
    }
}
