package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class loginPageObjects {
    WebDriver driver;
    WebDriverWait wait;
    By username=By.id("user-name");
    By password=By.id("password");
    By loginButton =By.id("login-button");
    By successIndicator=By.id("page_wrapper");
    //By errorMessage =By.xpath("//h3[@data-test='error']");
    By invalidUserErrorMessage =By.xpath("//h3[@data-test='error']");
    By lockUserErrorMessage=By.xpath(("//h3[text()='Sorry, this user has been locked out.']"));
    By problemUser=By.xpath("//img[@src='./img/sauce-backpack-1200x1500.jpgWithGarbageOnItToBreakTheUrl']");


    public loginPageObjects(WebDriver driver){
        //interact with browser
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }
    public void userName(String userName){
        driver.findElement(username).sendKeys(userName);
    }
    public void password(String password){
        driver.findElement(this.password).sendKeys(password);
    }
    public void loginButton(){
        driver.findElement(loginButton).click();
    }
    public WebElement verifySuccessfulLogin(){
        return driver.findElement(successIndicator);
    }
    public WebElement getErrorMessage(){
        return driver.findElement(invalidUserErrorMessage);
    }
    public WebElement lockUserErrorMessage(){
        return driver.findElement(lockUserErrorMessage);
    }
    public WebElement problemUser(){
        return driver.findElement(problemUser);
    }
}
