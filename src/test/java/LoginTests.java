import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() {
        LoginPage login = new LoginPage(getThreadLocal());
        login.loadLoginPage();
        login.clickSubmit();
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertFalse(homePage.avatarIsDisplayed());
    }
    @Test
    public void loginWrongPassword() {
        LoginPage login = new LoginPage(getThreadLocal());
        login.loadLoginPage();
        login.provideEmail("william.chang@testpro.io");
        login.providePassword("te$tStudentWW");
        login.clickSubmit();
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertFalse(homePage.avatarIsDisplayed());
    }

    @Test
    public void loginUpdateEmail() throws InterruptedException {
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        profilePage.updateEmail("te$tStudent1","william.chang1@testpro.io");
        profilePage.getSuccessNotification();
        Assert.assertEquals(profilePage.successNotificationText, "Profile updated.");
        profilePage.waitForSuccessNotificationDisappear();
        HomePage homePage = new HomePage(getThreadLocal());
        homePage.logOut();
        LoginPage newLogin = new LoginPage(getThreadLocal());
        newLogin.loadLoginPage();
        newLogin.provideEmail("william.chang1@testpro.io");
        newLogin.providePassword("te$tStudent1");
        newLogin.clickSubmit();
        Assert.assertTrue(homePage.avatarIsDisplayed());

        //revert for future tests
        profilePage.loadProfilePage();
        profilePage.updateEmail("te$tStudent1","william.chang@testpro.io");
        profilePage.getSuccessNotification();
        Assert.assertEquals(profilePage.successNotificationText, "Profile updated.");
    }

    @Test
    public void loginUpdatePassword() throws InterruptedException {
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        profilePage.updatePassword("te$tStudent1","test$tudent1");
        profilePage.getSuccessNotification();
        Assert.assertEquals(profilePage.successNotificationText, "Profile updated.");
        profilePage.waitForSuccessNotificationDisappear();
        HomePage homePage = new HomePage(getThreadLocal());
        homePage.logOut();
        LoginPage newLogin = new LoginPage(getThreadLocal());
        newLogin.loadLoginPage();
        newLogin.provideEmail("william.chang@testpro.io");
        newLogin.providePassword("test$tudent1");
        newLogin.clickSubmit();
        Assert.assertTrue(homePage.avatarIsDisplayed());

        //revert for future tests
        profilePage.loadProfilePage();
        profilePage.updatePassword("test$tudent1","te$tStudent1");
        profilePage.getSuccessNotification();
        Assert.assertEquals(profilePage.successNotificationText, "Profile updated.");
    }
    @Test
    public void verifyPasswordRequirements(){
        String password = "te$tStudent1";
        verifyPasswordEncrypted(password);
        verifyPasswordContainsSpecialCharacter(password);
        verifyPasswordContainsLowerCase(password);
        verifyPasswordContainsUpperCase(password);
        verifyPasswordLengthRequirments(password);
        verifyPasswordContainsNumber(password);
    }


    @Test
    public void verifyNineCharPasswordFails(){
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        profilePage.updatePassword("te$tStudent1","$Student1");
        profilePage.getErrorNotification();
        Assert.assertEquals(profilePage.errorNotificationText, "The new password must be at least 10 characters.");
        profilePage.waitForErrorNotificationDisappear();
    }
    @Test
    public void verifyNoSpecialCharPasswordFails(){
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        profilePage.updatePassword("te$tStudent1","testStudent1");
        profilePage.getErrorNotification();
        Assert.assertEquals(profilePage.errorNotificationText, "The new password must contain at least one symbol.");
        profilePage.waitForErrorNotificationDisappear();
    }
    @Test
    public void verifyNoNumberCharPasswordFails(){
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        profilePage.updatePassword("te$tStudent1","te$tStudentt");
        profilePage.getErrorNotification();
        Assert.assertEquals(profilePage.errorNotificationText, "The new password must contain at least one number.");
        profilePage.waitForErrorNotificationDisappear();
    }

    @Test
    public void verifyLongPassword(){
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        profilePage.updatePassword("te$tStudent1",
                "!1ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        profilePage.getSuccessNotification();
        Assert.assertEquals(profilePage.successNotificationText, "Profile updated.");
        profilePage.waitForSuccessNotificationDisappear();
        HomePage homePage = new HomePage(getThreadLocal());
        homePage.logOut();
        LoginPage newLogin = new LoginPage(getThreadLocal());
        newLogin.loadLoginPage();
        newLogin.provideEmail("william.chang@testpro.io");
        newLogin.providePassword("!1ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        newLogin.clickSubmit();
        Assert.assertTrue(homePage.avatarIsDisplayed());

        //revert for future tests
        profilePage.loadProfilePage();
        profilePage.updatePassword("!1ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz",
                "te$tStudent1");
        profilePage.getSuccessNotification();
        Assert.assertEquals(profilePage.successNotificationText, "Profile updated.");
    }

    public void verifyPasswordLengthRequirments(String password){
        Assert.assertTrue(password.length()>9);
    }

    public void verifyPasswordEncrypted(String password) {
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        ProfilePage profilePage = new ProfilePage(getThreadLocal());
        profilePage.loadProfilePage();
        Assert.assertNotEquals(profilePage.getSQLPasswordValue(), password);
        Assert.assertFalse(profilePage.getSQLPasswordValue().length() == password.length());
        //System.out.println(profilePage.getSQLPasswordValue());
    }


    public void verifyPasswordContainsSpecialCharacter(String password){
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(password);
        Assert.assertTrue(hasSpecial.find());
    }
    public void verifyPasswordContainsNumber(String password){
        Pattern number = Pattern.compile ("[0-9]");
        Matcher hasNumber = number.matcher(password);
        Assert.assertTrue(hasNumber.find());
    }


    public void verifyPasswordContainsLowerCase(String password){
        Pattern lower = Pattern.compile ("[a-z]");
        Matcher hasLowerCase = lower.matcher(password);
        Assert.assertTrue(hasLowerCase.find());
    }


    public void verifyPasswordContainsUpperCase(String password){
        Pattern upper = Pattern.compile ("[A-Z]");
        Matcher hasUpperCase = upper.matcher(password);
        Assert.assertTrue(hasUpperCase.find());
    }


    @Test
    public void loginTest() {

        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
    }


}
