package org.alfabank.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Text;
import io.appium.java_client.AppiumDriver;
import org.alfabank.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.qameta.allure.Step;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.alfabank.data.Attributes.HINT;
import static org.alfabank.data.Attributes.PASSWORD;

public class LoginPage extends BasePage {

    private static final String USERNAME_LABEL = "Логин";
    private static final String PASSWORD_LABEL = "Пароль";
    private static final String TITLE_TEXT = "Вход в Alfa-Test";
    private static final String CONFIRM_BUTTON_TEXT = "Вход";
    private static final String ERROR_MESSAGE = "Введены неверные данные";

    private final SelenideElement title = $(By.id("tvTitle"));
    private final SelenideElement usernameInput = $("android.widget.EditText[resource-id='com.alfabank.qapp:id/etUsername']");
    private final SelenideElement passwordInput = $(By.xpath("//android.widget.EditText[@resource-id='com.alfabank.qapp:id/etPassword']"));
    private final SelenideElement showPasswordButton = $(By.id("text_input_end_icon"));
    private final SelenideElement confirmButton = $(By.id("btnConfirm"));
    private final SelenideElement errorMessage = $(By.id("tvError"));

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Enter username: {}")
    public void setUsername(String value) {
        usernameInput.shouldBe(visible).sendKeys(value);
    }

    @Step("Get Username Input Value")
    public String getUsernameInputValue() {
        return usernameInput.getText();
    }

    @Step("Check Username Label")
    public void checkUsernameLabel() {
        usernameInput.shouldHave(attribute(HINT, USERNAME_LABEL));
    }

    @Step("Enter Password: {}")
    public void setPassword(String value) {
        passwordInput.shouldBe(visible).sendKeys(value);
    }

    @Step("Get Password Input Value")
    public String getPasswordInputValue() {
        return passwordInput.getText();
    }

    @Step("Check Password Input Value State")
    public void checkIsPasswordHidden(boolean isHidden) {
        passwordInput.shouldHave(attribute(PASSWORD, String.valueOf(isHidden)));
    }

    @Step("Check Password Label")
    public void checkPasswordLabel() {
        passwordInput.shouldHave(attribute(HINT, PASSWORD_LABEL));
    }

    @Step("Click Confirm Button")
    public void clickConfirmButton() {
        confirmButton.shouldBe(text(CONFIRM_BUTTON_TEXT)).shouldBe(visible).click();
    }

    @Step("Click Show Password Button")
    public void clickShowPasswordButton() {
        showPasswordButton.shouldBe(visible).click();
    }

    @Step("Click Show Password Button 'count' times")
    public void clickShowPasswordButton(int count) {
        for (int i = 0; i < count; i++) {
            clickShowPasswordButton();
        }
    }

    @Step("Check Title Text")
    public void checkTitleText() {
        title.shouldBe(text(TITLE_TEXT));
    }

    @Step("Get Error Message Text")
    public String getErrorMessageText() {
        return getErrorMessage().getText();
    }

    @Step("Get Error Message")
    private SelenideElement getErrorMessage() {
        return errorMessage.shouldBe(text(ERROR_MESSAGE));
    }

}
