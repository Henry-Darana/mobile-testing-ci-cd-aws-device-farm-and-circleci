package tests;

import io.appium.java_client.MobileElement;
import managers.OurDriverManager;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.Utils;

public class LoginTest extends OurDriverManager {

    @Test
    public void ableToLoginToKDS() {
        Utils.sleep(2000);
        Utils.openKDS();

        clickTrayImage().selectServer(hostName).clickLoginButton().loginToKDS(kdsUsername, kdsPassword)
                .selectVenueInVenueList(venueName).selectDevice(kdsDevice);

        Utils.sleep(5000);
        Utils.openPOS();
        Utils.sleep(5000);
    }

    @Test(dependsOnMethods = {"ableToLoginToKDS"})
    public void ableToLoginViaQuickCode() {
        Utils.sleep(5000);
        Utils.openPOS();
        enterQuickCode(quickCode).clickContinueButton();
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

    public LoginTest enterQuickCode(String quickCode) {
        MobileElement btn1 = driver.findElementById("com.vendsy.tray:id/button_1");
        MobileElement btn2 = driver.findElementById("com.vendsy.tray:id/button_2");
        MobileElement btn3 = driver.findElementById("com.vendsy.tray:id/button_3");
        MobileElement btn4 = driver.findElementById("com.vendsy.tray:id/button_4");
        MobileElement btn5 = driver.findElementById("com.vendsy.tray:id/button_5");
        MobileElement btn6 = driver.findElementById("com.vendsy.tray:id/button_6");
        MobileElement btn7 = driver.findElementById("com.vendsy.tray:id/button_7");
        MobileElement btn8 = driver.findElementById("com.vendsy.tray:id/button_8");
        MobileElement btn9 = driver.findElementById("com.vendsy.tray:id/button_9");
        MobileElement btn0 = driver.findElementById("com.vendsy.tray:id/button_0");


        char[] quickCodeArray = quickCode.toCharArray();
        int i = 0;
        while (i <= quickCodeArray.length - 1) {
            switch (quickCodeArray[i]) {
                case '1':
                    btn1.click();
                    break;
                case '2':
                    btn2.click();
                    break;
                case '3':
                    btn3.click();
                    break;
                case '4':
                    btn4.click();
                    break;
                case '5':
                    btn5.click();
                    break;
                case '6':
                    btn6.click();
                    break;
                case '7':
                    btn7.click();
                    break;
                case '8':
                    btn8.click();
                    break;
                case '9':
                    btn9.click();
                    break;
                case '0':
                    btn0.click();
                    break;
            }
            i++;
        }

        return this;
    }

    public LoginTest clickContinueButton() {
        MobileElement btnContinue = driver.findElementById("com.vendsy.tray:id/submit_button");
        btnContinue.click();
        return this;
    }
}
