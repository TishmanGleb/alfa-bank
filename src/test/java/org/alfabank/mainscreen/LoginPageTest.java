package org.alfabank.mainscreen;

import lombok.SneakyThrows;
import org.alfabank.core.AbstractTest;
import org.alfabank.pages.LoginPage;
import org.alfabank.pages.MainPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.alfabank.utils.StringUtils.generateRandomString;

public class LoginPageTest extends AbstractTest {

    private static final String ALLOWED_CHARACTER_SET_REGEX = "^[a-zA-Z .,/'_-]+$";
    private static final String USERNAME = "Login";
    private static final String PASSWORD = "Password";
    private static final String TITLE_TEXT = "Вход в Alfa-Test выполнен";
    private static final String ERROR_MESSAGE = "Введены неверные данные";

    @Test(description = "Check login with valid credentials")
    public void loginWithValidCredentialsTest() {
        loginPage.checkTitleText();
        loginPage.setUsername(USERNAME);
        loginPage.checkUsernameLabel();
        loginPage.setPassword(PASSWORD);
        loginPage.checkPasswordLabel();
        loginPage.checkIsPasswordHidden(true);
        loginPage.clickConfirmButton();
        String actualTitleText = mainPage.getTitleText();
        Assert.assertEquals(actualTitleText, TITLE_TEXT);
    }

    @Test(description = "Check login with invalid credentials")
    public void loginWithInvalidCredentialsTest() {
        loginPage.setUsername(generateRandomString(5));
        loginPage.setPassword(generateRandomString(5));
        loginPage.clickConfirmButton();
        String actualErrorMessage = loginPage.getErrorMessageText();
        Assert.assertEquals(actualErrorMessage, ERROR_MESSAGE);
    }

    @Test(description = "Check username input max value")
    public void usernameInputMaxValueTest() {
        String userName = generateRandomString(60);
        loginPage.setUsername(userName);
        int actualUsernameInputValueLength = loginPage.getUsernameInputValue().length();
        Assert.assertEquals(actualUsernameInputValueLength, 50);
    }

    @Test(description = "Check password input max value")
    public void passwordInputMaxValueTest() {
        String password = generateRandomString(60);
        loginPage.setPassword(password);
        int actualPasswordInputValueLength = loginPage.getPasswordInputValue().length();
        Assert.assertEquals(actualPasswordInputValueLength, 50);
    }

    @Test(description = "Check username input allowed character set")
    public void usernameInputAllowedCharacterSetTest() {
        String userName = "*%45A$@";
        loginPage.setUsername(userName);
        String actualUsernameInputValue = loginPage.getUsernameInputValue();
        Assert.assertTrue(actualUsernameInputValue.matches(ALLOWED_CHARACTER_SET_REGEX));
    }

    @Test(description = "Check password input state")
    public void passwordInputStateTest() {
        loginPage.setPassword(generateRandomString(5));
        loginPage.checkIsPasswordHidden(true);
        loginPage.clickShowPasswordButton(5);
        loginPage.checkIsPasswordHidden(false);

        loginPage.clickShowPasswordButton(5);
        loginPage.checkIsPasswordHidden(true);
    }
}
