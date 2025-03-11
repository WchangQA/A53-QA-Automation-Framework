package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.sql.*;

public class ProfilePage extends BasePage {
    @FindBy(xpath="//input[@id='inputProfileCurrentPassword']")
    private WebElement currentPasswordField;
    @FindBy(xpath="//input[@id='inputProfileNewPassword']")
    private WebElement updatedPasswordField;
    @FindBy(xpath="//input[@id='inputProfileEmail']")
    private WebElement emailField;
    @FindBy(xpath="//button[@class='btn-submit']")
    private WebElement submitBtn;
    @FindBy(xpath="//*[@class='success show']")
    private WebElement successNotification;
    @FindBy(xpath="//*[@class='error show']")
    private WebElement errorNotification;
    public boolean isSuccessNotificationActive;
    public String successNotificationText;
    public boolean isErrorNotificationActive;
    public String errorNotificationText;

    static final String DB_URL = "jdbc:mysql://104.237.13.60:3306/dbkoel";
    static final String USER = "dbuser08";
    static final String PASS = "pa$$08";
    static final String QUERY = "SELECT password FROM users WHERE email = 'william.chang@testpro.io'";

    public ProfilePage(WebDriver givenDriver) {
        super(givenDriver);
        isSuccessNotificationActive = false;
        successNotificationText = " ";
    }
    public ProfilePage loadProfilePage(){
        String url = "https://qa.koel.app/#!/profile";
        driver.get(url);
        return this;
    }

    public ProfilePage updateEmail(String currPassword, String newEmail){
        providePassword(currPassword);
        provideEmail(newEmail);
        clickSave();
        return this;
    }

    public ProfilePage updatePassword(String currPassword, String newPassword){
        providePassword(currPassword);
        provideNewPassword(newPassword);
        clickSave();
        return this;
    }

    public ProfilePage provideEmail(String email) {
        emailField.sendKeys(Keys.chord(Keys.COMMAND,"a",Keys.DELETE));
        emailField.sendKeys(email);
        return this;
    }

    public ProfilePage providePassword (String password){
        currentPasswordField.sendKeys(password);
        return this;
    }
    public ProfilePage provideNewPassword (String password){
        updatedPasswordField.sendKeys(password);
        return this;
    }

    public ProfilePage clickSave(){
        submitBtn.click();
        return this;
    }
    public ProfilePage getSuccessNotification(){
        WebElement successNotificationWait = findElement(successNotification);
        isSuccessNotificationActive = successNotificationWait.isDisplayed();
        successNotificationText = successNotificationWait.getText();
        return this;
    }
    public boolean waitForSuccessNotificationDisappear(){
        return findElementDisappear(successNotification);
    }
    public ProfilePage getErrorNotification(){
        WebElement errorNotificationWait = findElement(errorNotification);
        isErrorNotificationActive = errorNotificationWait.isDisplayed();
        errorNotificationText = errorNotificationWait.getText();
        return this;
    }
    public boolean waitForErrorNotificationDisappear(){
        return findElementDisappear(errorNotification);
    }

    public String getSQLPasswordValue(){

        /*String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            if (rs.next()) {
                return (rs.getString("password"));
                // Retrieve by column name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }


}
