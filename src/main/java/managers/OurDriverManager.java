package managers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class OurDriverManager {

    public static AndroidDriver<MobileElement> driver;

    @BeforeSuite
    private void setUpAppium() throws MalformedURLException {

        final String urlRemote = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(urlRemote);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        //desiredCapabilities.setCapability("orientation", "LANDSCAPE");
        driver = new AndroidDriver<MobileElement>(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAppium() {
        driver.quit();
    }


}
