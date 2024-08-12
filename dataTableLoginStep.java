package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class dataTableLoginStep {
    WebDriver driver;
    @Given("User open the login page")
    public void user_open_the_login_page() {
        driver=new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/index.html");

    }
    @When("User enter {string} and {string}")
    public void user_enter_and(String username, String Password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(Password);

    }
    @When("Click Login button")
    public void click_login_button() throws InterruptedException {
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        driver.quit();

    }

}
