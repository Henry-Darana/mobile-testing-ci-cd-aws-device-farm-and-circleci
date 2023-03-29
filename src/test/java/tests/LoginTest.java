package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static final String SERVER_NAME = "//android.widget.CheckedTextView[contains(@text, '<serverName>')]";
    public static final String VENUE_NAME = "//android.widget.TextView[contains(@text, '<venueName>')]";
    public static AndroidDriver<MobileElement> driver;
    String hostName = "https://qa.vendsy.com/";
    String kdsUsername = "qa_automation_kds_login_2@gmail.com";
    String kdsPassword = "KDSLogin#2";
    String venueName = "Automation_2021";
    //MobileElement pnlVenues = driver.findElementById("android:id/parentPanel");
    //By buttonLogin = By.id("com.vendsy.tray.pos:id/loginButton");
    String kdsDevice = "POS4";

    @BeforeSuite
    private void setUpAppium() throws MalformedURLException {

        final String urlRemote = "http://127.0.0.1:4723/wd/hub";
        URL url = new URL(urlRemote);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        try {
            driver = new AndroidDriver<MobileElement>(url, desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDownAppium() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ableToLogin() {


        Utils.sleep(2000);
        driver.activateApp("com.vendsy.tray.pos");
        driver.rotate(ScreenOrientation.LANDSCAPE);


        clickTrayImage().selectServer(hostName).clickLoginButton()
                .loginToKDS(kdsUsername, kdsPassword).selectVenueInVenueList(venueName)
                .selectDevice(kdsDevice);
    }


    public LoginTest clickTrayImage() {
        Utils.sleep(5000);
        MobileElement imgTray = driver.findElementById("developer_mode");
        for (int i = 1; i <= 5; i++)
            imgTray.click();
        return this;
    }

    public LoginTest selectServer(String serverName) {
        Utils.sleep(5000);
        MobileElement drpServers = driver.findElementById("android:id/text1");
        drpServers.click();
        MobileElement e = driver.findElementByXPath(SERVER_NAME.replace("<serverName>", serverName));
        e.click();
        return this;
    }


    public LoginTest clickLoginButton() {
        Utils.sleep(5000);
        MobileElement btnLogin = driver.findElementById("submitButton");
        btnLogin.click();
        return this;
    }

    public LoginTest loginToKDS(String username, String password) {
        Utils.sleep(5000);
        MobileElement txtUsername = driver.findElementById("dialog_login_username");
        MobileElement txtPassword = driver.findElementById("dialog_login_password");
        MobileElement btnSubmit = driver.findElementById("submitButton");
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return this;
    }

    public LoginTest selectVenueInVenueList(String venueName) {
        Utils.sleep(5000);
        MobileElement e = driver.findElementByXPath(VENUE_NAME.replace("<venueName>", venueName));
        e.click();
        return this;
    }

    public LoginTest selectDevice(String deviceName) {
        Utils.sleep(5000);
        WebElement selDevices = driver.findElementById("kdsList");
        Select select = new Select(selDevices);
        select.selectByVisibleText(deviceName);
        return this;
    }

}
