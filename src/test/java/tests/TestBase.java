package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(false).savePageSource(false));
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = null;
        try {
            sessionId = Selenide.sessionId().toString();
            System.out.println(sessionId);
        } catch (Exception ignored) {
        }
        closeWebDriver();
        if (sessionId != null) {
            try {
                Attach.addVideo(sessionId);
            } catch (Exception ignored) {
            }
        }
    }
}
