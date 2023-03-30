package tests;

import io.appium.java_client.MobileElement;
import managers.OurDriverManager;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.Utils;

public class LoginTest extends OurDriverManager {

    @Test
    public void ableToLogin() {
        Utils.sleep(2000);
        driver.activateApp("com.vendsy.tray.pos");

        clickTrayImage().selectServer(hostName).clickLoginButton().loginToKDS(kdsUsername, kdsPassword)
                .selectVenueInVenueList(venueName).selectDevice(kdsDevice);
    }

    public LoginTest clickTrayImage() {
        MobileElement imgTray = driver.findElementById("developer_mode");
        for (int i = 1; i <= 5; i++)
            imgTray.click();
        return this;
    }

    public LoginTest selectServer(String serverName) {
        MobileElement drpServers = driver.findElementById("android:id/text1");
        drpServers.click();
        MobileElement e = driver.findElementByXPath(SERVER_NAME.replace("<serverName>", serverName));
        e.click();
        return this;
    }


    public LoginTest clickLoginButton() {
        MobileElement btnLogin = driver.findElementById("loginButton");
        btnLogin.click();
        return this;
    }

    public LoginTest loginToKDS(String username, String password) {
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
        WebElement selDevices = driver.findElementById("devicesListView");
        MobileElement e = driver.findElementByXPath(DEVICE_NAME.replace("<deviceName>", deviceName));
        e.click();
        return this;
    }

}
