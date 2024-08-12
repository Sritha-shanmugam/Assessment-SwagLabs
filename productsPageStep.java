package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class productsPageStep {
    private WebDriver driver;
    private String selectedProductName;
    private int productCount = 0;
    private final int PRODUCTS_TO_ADD = 5;

    @Given("User is on the SWAGLabs login page")
    public void user_is_on_the_SWAGLabs_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
        productCount = 0; // Reset product count for each scenario
    }

    @When("User logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User clicks on every product and verifies the name and amount")
    public void user_clicks_on_every_product_and_verifies_the_name_and_amount() {
        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        for (WebElement product : products) {
            String productName = product.findElement(By.className("inventory_item_name")).getText();
            String productPrice = product.findElement(By.className("inventory_item_price")).getText();

            product.findElement(By.className("inventory_item_name")).click();

            String detailedProductName = driver.findElement(By.className("inventory_details_name")).getText();
            Assert.assertEquals(detailedProductName, productName, "Product names do not match!");

            String detailedProductPrice = driver.findElement(By.className("inventory_details_price")).getText();
            Assert.assertEquals(detailedProductPrice, productPrice, "Product prices do not match!");

            driver.navigate().back();
            products = driver.findElements(By.className("inventory_item"));
            System.out.println("Products name = " + productName + " & Products price = " + productPrice);
        }
    }

    @And("User clicks on any product and adds it to the cart")
    public void user_Clicks_On_Any_Product_And_Adds_It_To_The_Cart() {
        WebElement firstProduct = driver.findElement(By.className("inventory_item_name"));
        selectedProductName = firstProduct.getText();
        firstProduct.click();
        driver.findElement(By.className("btn_inventory")).click();
        productCount++;
    }

    @Then("Verify the product is visible in the cart using the product name")
    public void verify_The_Product_Is_Visible_In_The_Cart_Using_The_ProductName() {
        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartProducts = driver.findElements(By.className("inventory_item_name"));
        boolean productFound = cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equals(selectedProductName));

        Assert.assertTrue(productFound, "The product in the cart does not match the selected product!");
        System.out.println("The selected product is = " + selectedProductName);
    }

    @And("User clicks on continue shopping and selects the next product")
    public void user_Clicks_On_Continue_Shopping_And_Selects_The_Next_Product() {
        driver.findElement(By.xpath("//a[@class='btn_secondary']")).click();
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        WebElement nextProduct = products.get(1); // Get the second product
        selectedProductName = nextProduct.getText(); // Store the new product name
        nextProduct.click();
        driver.findElement(By.xpath("//*[@class='btn_primary btn_inventory']")).click(); // Add the product to the cart
        productCount++;
    }

    @Then("Verify the total count of the products in the cart")
    public void verify_The_Total_Count_Of_The_Products_In_The_Cart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int actualProductCount = cartItems.size();
        System.out.println("Total No. of Products in cart = " + actualProductCount);

        Assert.assertEquals(actualProductCount, productCount, "The total count of products in the cart is incorrect!");
    }

    @And("User removes a product from the cart")
    public void user_Removes_A_Product_From_The_Cart() {
        WebElement removeButton = driver.findElement(By.xpath("//div[@class='item_pricebar'][div[@class='inventory_item_price' and text()='9.99']]//button[@class='btn_secondary cart_button']"));
        removeButton.click();
        productCount--;
    }

    @Then("Verify the total count of the products in the cart after removal")
    public void verify_The_Total_Count_Of_The_Products_In_The_Cart_After_Removal() {
        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int actualProductCount = cartItems.size();
        System.out.println("Total No. of Products in cart after removal = " + actualProductCount);

        Assert.assertEquals(actualProductCount, productCount, "The total count of products in the cart after removal is incorrect!");
        WebElement removeButton = driver.findElement(By.xpath("//div[@class='item_pricebar'][div[@class='inventory_item_price' and text()='29.99']]//button[@class='btn_secondary cart_button']"));
        removeButton.click();
        productCount--;
        WebElement clickContinue= driver.findElement(By.xpath("//*[@class='btn_secondary']"));
        clickContinue.click();
    }

    @Then("User adds 5 products to the cart")
    public void user_adds_5_products_to_the_cart() {

        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        for (int i = 0; i < PRODUCTS_TO_ADD && i < products.size(); i++) {
            WebElement product = products.get(i);
            product.findElement(By.className("btn_inventory")).click(); // Add to cart
            productCount++;
        }

    }

    @Then("Verify the total count of products in the cart matches the number added")
    public void verify_the_total_count_of_products_in_the_cart() throws InterruptedException {
        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int actualProductCount = cartItems.size();
        Thread.sleep(3000);

        Assert.assertEquals(actualProductCount, productCount, "The total count of products in the cart is incorrect!");
        System.out.println("Total No. of Products in cart = " + actualProductCount);
    }

    @And("User removes all products from the cart")
    public void user_removes_all_products_from_the_cart() {
        driver.findElement(By.className("shopping_cart_link")).click(); // Go to cart
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[@class='btn_secondary cart_button']"));

        for (WebElement removeButton : removeButtons) {
            removeButton.click(); // Click each REMOVE button
        }
    }

    @Then("User add Two Products And Proceed To Checkout")
    public void user_Add_Two_Products_And_Proceed_To_Checkout() {
        WebElement clickContinue=driver.findElement(By.className("btn_secondary"));
        clickContinue.click();
        WebElement product1=driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
        product1.click();
        WebElement addToCart=driver.findElement(By.xpath("//*[@class='btn_primary btn_inventory']"));
        addToCart.click();
        WebElement goBack=driver.findElement(By.className("inventory_details_back_button"));
        goBack.click();
        WebElement product2=driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div"));
        product2.click();
        driver.findElement(By.xpath("//*[@class='btn_primary btn_inventory']")).click();
        WebElement clickCart=driver.findElement(By.className("shopping_cart_link"));
        clickCart.click();

        WebElement checkoutButton = driver.findElement(By.xpath("//*[@class='btn_action checkout_button']"));
        checkoutButton.click();
        // Enter details
        driver.findElement(By.id("first-name")).sendKeys("Sritha");
        driver.findElement(By.id("last-name")).sendKeys("P");
        driver.findElement(By.id("postal-code")).sendKeys("9749759990");
        // Click 'Continue'
        driver.findElement(By.xpath("//input[@class='btn_primary cart_button']")).click();
    }
    @Then("Verify the total value of the products in the cart")
    public void verify_The_Total_Value_Of_The_Products_In_The_Cart() {
        WebElement totalElement= driver.findElement(By.className("summary_total_label"));
        String totalText=totalElement.getText().replace("$","").trim();
        try {
            double displayedTotal = Double.parseDouble(totalText);
            System.out.println("Displayed Total Value is : $ " + displayedTotal);
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse total value: " + e.getMessage());
        }
        WebElement finish= driver.findElement(By.xpath("//a[@class='btn_action cart_button']"));
        finish.click();
        WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(confirmationMessage.getText(), "THANK YOU FOR YOUR ORDER", "Order confirmation message is incorrect!");

        System.out.println("Order confirmation message is displayed as expected.");
        driver.quit();

    }


}