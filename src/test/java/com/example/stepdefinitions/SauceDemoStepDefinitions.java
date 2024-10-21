package com.example.stepdefinitions;

import com.example.models.Item;
import com.example.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SauceDemoStepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CommonElements commonElements;

    private List<Item> selectedItems;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        selectedItems = new ArrayList<>();
    }

    @Given("I am on the Sauce Demo login page")
    public void i_am_on_the_sauce_demo_login_page() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, loginPage);
    }

    @When("I log in with standard_user credentials")
    public void i_log_in_with_standard_user_credentials() {
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    @Then("I should be on the products page")
    public void i_should_be_on_the_products_page() {
        PageFactory.initElements(driver, productsPage);
        assertTrue(productsPage.isOnProductsPage());
    }

    @When("I add two specific products to the cart")
    public void i_add_two_specific_products_to_the_cart() {
        productsPage = new ProductsPage(driver);
        PageFactory.initElements(driver, productsPage);
        List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");
        productsPage.addProductToCartByName(productsToAdd);

        selectedItems.addAll(productsPage.getSelectedItems());
    }

    @Then("the products should be successfully added to the cart without going to the cart")
    public void the_products_should_be_successfully_added_to_the_cart_without_going_to_the_cart() {
        List<String> selectedItemsNames = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");
        boolean areInCart = productsPage.areProductsInCart(selectedItemsNames);

        assertTrue(areInCart, "One or more products are not displayed in the cart.");
    }

    @When("I remove the products from the cart")
    public void i_remove_the_products_from_the_cart() {
        productsPage = new ProductsPage(driver);
        PageFactory.initElements(driver, productsPage);
        List<String> productsToRemove = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");
        productsPage.removeProductsFromCartByNames(productsToRemove);
        
        selectedItems.removeIf(item -> productsToRemove.contains(item.getName()));
    }

    @Then("the products should be successfully removed from the cart without going to the cart")
    public void the_products_should_be_successfully_removed_from_the_cart_without_going_to_the_cart() {
        List<String> selectedItemsNames = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");
        boolean areNotInCart = productsPage.areProductsNotInCart(selectedItemsNames);

        assertTrue(areNotInCart, "One or more products are displayed in the cart.");
    }

    @When("I add two different products to the cart")
    public void i_add_two_different_products_to_the_cart() {
        productsPage = new ProductsPage(driver);
        PageFactory.initElements(driver, productsPage);
        List<String> productsToAdd = Arrays.asList("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCartByName(productsToAdd);

        selectedItems.addAll(productsPage.getSelectedItems());
    }

    @When("I go to the cart")
    public void i_go_to_the_cart() {
        commonElements = new CommonElements(driver);
        cartPage = new CartPage(driver);
        PageFactory.initElements(driver, cartPage);

        commonElements.goToCart();
    }

    @Then("I should see the selected products displayed correctly in the cart")
    public void i_should_see_the_selected_products_displayed_correctly_in_the_cart() {
        List<String> expectedItems = selectedItems.stream()
                .map(Item::getName).toList();

        // System.out.println("Expected items in cart: " + expectedItems);

        for (String expectedItem : expectedItems) {
            assertTrue(cartPage.isItemInCart(expectedItem), "Expected item not found in the cart: " + expectedItem);
        }
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.clickCheckout();
    }

    @When("I try to submit the empty form")
    public void i_try_to_submit_the_empty_form() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.submitForm("", "", "");
    }

    @Then("I should see appropriate error messages")
    public void i_should_see_appropriate_error_messages() {
        checkoutPage = new CheckoutPage(driver);

        assertTrue(checkoutPage.hasErrorMessage());
    }

    @When("I fill in the checkout form with valid information")
    public void i_fill_in_the_checkout_form_with_valid_information() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.submitForm("Nikolay", "Kashev", "4004");
    }

    @Then("the information on the last preview screen is shown properly")
    public void the_information_on_the_last_preview_screen_is_shown_properly() {
        List<String> expectedItemNames = selectedItems.stream()
            .map(Item::getName).toList();

        List<String> expectedItemDescriptions = selectedItems.stream()
            .map(Item::getDescription).toList();

        List<Double> expectedItemPrices = selectedItems.stream()
            .map(item -> Double.parseDouble(item.getPrice().replace("$", "").trim())).toList();

        // double totalExpectedPrice = expectedItemPrices.stream()
        //     .mapToDouble(Double::doubleValue)
        //     .sum();

        boolean checkItemDetails = checkoutPage.checkItemDetails(expectedItemNames, expectedItemDescriptions, expectedItemPrices);
        
        // System.out.println("Expected Items Names: " + expectedItemNames);
        // System.out.println("Expected Items Descriptions: " + expectedItemDescriptions);
        // System.out.println("Expected Items Prices: " + expectedItemPrices);
        // System.out.println("Expected Items Total Price: " + totalExpectedPrice);

        assertTrue(checkItemDetails, "The information on the preview screen is incorrect.");
        assertTrue(checkoutPage.checkTotalPrice(expectedItemPrices), "The expected items price does not match the actual items price.");
        assertTrue(checkoutPage.checkButtonsVisibility(), "Cancel or Finish button is/are not visible.");
    }

    @When("I complete the purchase")
    public void i_complete_the_purchase() {
        checkoutPage.clickFinish();
    }

    @Then("I should see a purchase success message")
    public void i_should_see_a_purchase_success_message() {
        assertTrue(checkoutPage.hasSuccessMessage("Thank you for your order!", 
        "Your order has been dispatched, and will arrive just as fast as the pony can get there!"));
    }

    @When("I log out")
    public void i_log_out() {
        commonElements.logout();
    }

    @Then("I should be back on the login page")
    public void i_should_be_back_on_the_login_page() {
        // System.out.println(driver.getCurrentUrl());
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "The URL does not match the expected login page URL.");
        assertTrue(commonElements.isLoginButtonDisplayed());
    }

    @After
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
