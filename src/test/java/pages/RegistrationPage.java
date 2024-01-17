package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By emailField = By.xpath("//input[@type='email']");
    By passwordField = By.xpath("//input[@type='password']");
    By submitBtn = By.xpath("//button[@type='submit']");

    By registrationBtn = By.xpath("//a[@href='registration']");

    public String getPageUrl(){
        return  "https://qa.koel.app/registration";
    }
    public void provideEmail(String email) {
        findElement(emailField).sendKeys(email);
    }

    public void providePassword (String password){
        findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit(){
        findElement(submitBtn).click();
    }

    public void clickRegistration(){
        findElement(registrationBtn).click();
    }

}
