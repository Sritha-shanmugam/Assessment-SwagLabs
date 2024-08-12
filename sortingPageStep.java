package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class sortingPageStep {

    WebDriver driver; // Initialize this with your WebDriver setup


    @Given("^User is on the SWAGLabs product page$")
    public void user_is_on_the_SWAGLabs_product_page() {
        System.getProperty("Webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        // Navigate to the Swag Labs website and log in
        driver.get("https://www.saucedemo.com/v1/index.html");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }


    @When("^User sorts products by Name ascending$")
    public void userSortsProductsByNameAscending() throws InterruptedException {

        WebElement sort=driver.findElement(By.className("product_sort_container"));
        sort.click();
        Select select=new Select(sort);
        select.selectByVisibleText("Name (A to Z)");
        System.out.println("Sorting A to Z:" );
        printProductNames();
        Thread.sleep(2000);
        driver.quit();

    }

    @When("User sorts products by Name decending")
    public void user_Sorts_Products_By_Name_Descending() throws InterruptedException {
        WebElement sort=driver.findElement(By.className("product_sort_container"));
        sort.click();
        Select select=new Select(sort);
        select.selectByVisibleText("Name (Z to A)");
        System.out.println("Sorting Z to A:" );
        printProductNames();
        Thread.sleep(2000);
        driver.quit();
    }

    @When("^User sorts products by Price ascending$")
    public void user_Sorts_Products_By_Price_Ascending() throws InterruptedException {
        WebElement sort=driver.findElement(By.className("product_sort_container"));
        sort.click();
        Select select=new Select(sort);
        select.selectByVisibleText("Price (low to high)");
        System.out.println("Sorting with price low to high:" );
        printProductNames();
        Thread.sleep(2000);
        driver.quit();
    }

    @When("^User sorts products by Price desending$")
    public void user_Sorts_Products_By_Price_Desending() throws InterruptedException {
        List<WebElement> beforeFilterPrice=driver.findElements(By.className("inventory-item-price"));
        List<Double>beforeFilterPriceList=new ArrayList<>();
        for (WebElement p : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        WebElement sort=driver.findElement(By.className("product_sort_container"));
        sort.click();
        Select select=new Select(sort);
        select.selectByVisibleText("Price (high to low)");
        Thread.sleep(2000);
        List<WebElement> afterFilterPrice=driver.findElements(By.className("inventory-item-price"));
        List<Double> afterFilterPriceList=new ArrayList<>();
        for (WebElement p : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$","")));
        }
        Collections.sort(beforeFilterPriceList);
        Collections.reverse(beforeFilterPriceList);
        Assert.assertEquals(beforeFilterPrice,afterFilterPriceList);
        System.out.println("Sorting with price high to low:" );
        printProductNames();

        driver.quit();
    }
    private void printProductNames() {
        // Locate all product name elements on the page
        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));

        System.out.println("Products after sorting:");
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println(productNames.get(i).getText() + " - " + productPrices.get(i).getText());
        }
    }

}

