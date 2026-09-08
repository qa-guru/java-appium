package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static helpers.Browserstack.KEY;
import static helpers.Browserstack.USER;

public class SearchTests_old {

    @Test
    void successfulSearchTest() throws MalformedURLException, InterruptedException {

        MutableCapabilities caps = new MutableCapabilities();

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", USER);
        bstackOptions.put("accessKey", KEY);
        bstackOptions.put("projectName", "First Java Project");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("sessionName", "first_test");
        bstackOptions.put("deviceName", "Samsung Galaxy S22 Ultra");
        bstackOptions.put("osVersion", "12.0");
        bstackOptions.put("appiumVersion", "2.6.0");

        caps.setCapability("platformName", "android");
        caps.setCapability("appium:app", "bs://sample.app");
        caps.setCapability("bstack:options", bstackOptions);

        RemoteWebDriver driver = new RemoteWebDriver(
                new URL("https://" + USER + ":" + KEY + "@hub.browserstack.com/wd/hub"), caps);

        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Search Wikipedia")));
        searchElement.click();
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("Appium");
        Thread.sleep(5000);
        List<WebElement> allProductsName = driver.findElements(AppiumBy.className(
                "android.widget.TextView"));
        assert (allProductsName.size() > 0);

        driver.quit();

    }
}
