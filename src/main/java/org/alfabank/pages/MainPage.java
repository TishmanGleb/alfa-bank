package org.alfabank.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.alfabank.core.BasePage;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private static final String TITLE_TEXT = "Вход в Alfa-Test выполнен";

    private SelenideElement title = $(By.xpath("//android.widget.TextView[@text='" + TITLE_TEXT + "']"));

    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Get Title Text")
    public String getTitleText() {
        return title.getText();
    }
}
