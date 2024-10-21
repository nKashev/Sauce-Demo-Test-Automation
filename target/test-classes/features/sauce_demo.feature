<<<<<<< HEAD
Feature: Sauce Demo E-commerce Functionality

  Scenario: Complete purchase flow
    Given I am on the Sauce Demo login page
    When I log in with standard_user credentials
    Then I should be on the products page
    When I add two specific products to the cart
    Then the products should be successfully added to the cart without going to the cart
    When I remove the products from the cart
    Then the products should be successfully removed from the cart without going to the cart
    When I add two different products to the cart
    And I go to the cart
    Then I should see the selected products displayed correctly in the cart
    When I proceed to checkout
    And I try to submit the empty form
    Then I should see appropriate error messages
    When I fill in the checkout form with valid information
    Then the information on the last preview screen is shown properly
    When I complete the purchase
    Then I should see a purchase success message
    When I log out
=======
Feature: Sauce Demo E-commerce Functionality

  Scenario: Complete purchase flow
    Given I am on the Sauce Demo login page
    When I log in with standard_user credentials
    Then I should be on the products page
    When I add two specific products to the cart
    Then the products should be successfully added to the cart without going to the cart
    When I remove the products from the cart
    Then the products should be successfully removed from the cart without going to the cart
    When I add two different products to the cart
    And I go to the cart
    Then I should see the selected products displayed correctly in the cart
    When I proceed to checkout
    And I try to submit the empty form
    Then I should see appropriate error messages
    When I fill in the checkout form with valid information
    Then the information on the last preview screen is shown properly
    When I complete the purchase
    Then I should see a purchase success message
    When I log out
>>>>>>> 85d468a (Upload project files with src and test folders)
    Then I should be back on the login page