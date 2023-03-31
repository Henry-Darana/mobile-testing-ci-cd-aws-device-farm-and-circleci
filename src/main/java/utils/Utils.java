package utils;

import managers.OurDriverManager;
import org.openqa.selenium.ScreenOrientation;

public class Utils extends OurDriverManager {

    public static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
        }
    }

    public static void openKDS() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.activateApp("com.vendsy.tray.pos");
    }

    public static void openPOS() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.activateApp("com.vendsy.tray");
    }
}
