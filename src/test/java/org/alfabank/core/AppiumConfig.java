package org.alfabank.core;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@ComponentScan(basePackages = {"org.alfabank"})
@PropertySources(@PropertySource("common.properties"))
public class AppiumConfig {

}
