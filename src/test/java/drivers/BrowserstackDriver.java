package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static helpers.Browserstack.KEY;
import static helpers.Browserstack.USER;

public class BrowserstackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", USER);
        bstackOptions.put("accessKey", KEY);
        bstackOptions.put("projectName", "First Java Project");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("sessionName", "first_test");
        bstackOptions.put("deviceName", "Samsung Galaxy S22 Ultra");
        bstackOptions.put("osVersion", "12.0");

        caps.setCapability("platformName", "android");
        caps.setCapability("appium:app", "bs://sample.app");
        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(
                    new URL("https://" + USER + ":" + KEY + "@hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
