Feature: SWAGLabs Shopping Flow

  Scenario: Products verification on SWAGLabs
    Given User is on the SWAGLabs login page
    When User logs in with valid credentials
    Then User clicks on every product and verifies the name and amount
    And User clicks on any product and adds it to the cart
    Then Verify the product is visible in the cart using the product name
    And User clicks on continue shopping and selects the next product
    Then Verify the total count of the products in the cart
    And User removes a product from the cart
    Then Verify the total count of the products in the cart after removal
    Then User adds 5 products to the cart
    Then Verify the total count of products in the cart matches the number added
    And User removes all products from the cart
    Then User add Two Products And Proceed To Checkout
    Then Verify the total value of the products in the cart








