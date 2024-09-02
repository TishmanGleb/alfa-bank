package org.alfabank.core;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.testng.AllureTestNg;
import lombok.SneakyThrows;
import org.alfabank.pages.LoginPage;
import org.alfabank.pages.MainPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.net.URL;

@Listeners(AllureTestNg.class)
@ContextConfiguration(classes = AppiumConfig.class)
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    @Value("${appiumURL}")
    private String appiumURL;
    @Value("${platformName}")
    private String platformName;
    @Value("${deviceName}")
    private String deviceName;
    @Value("${app}")
    private String app;
    @Value("${automationName}")
    private String automationName;

    private AppiumDriver driver;
    protected LoginPage loginPage;
    protected MainPage mainPage;

    @SneakyThrows
    @BeforeMethod
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", app);
        capabilities.setCapability("automationName", automationName);
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        WebDriverRunner.setWebDriver(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println();
        if (driver != null) {
            driver.quit();
        }
    }
}
