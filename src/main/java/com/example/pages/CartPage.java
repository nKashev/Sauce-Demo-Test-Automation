<<<<<<< HEAD
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private By cartItems = By.cssSelector("[data-test='inventory-item']");
    private By itemQuantity = By.cssSelector("[data-test='item-quantity']");
    private By itemName = By.cssSelector("[data-test='inventory-item-name']");
    private By itemPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By removeButton = By.cssSelector("[class='btn btn_secondary btn_small cart_button']");
    private By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");
    private By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Get list of all items in the cart
    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    // Get the quantity of a specific item
    public String getItemQuantity(WebElement item) {
        return item.findElement(itemQuantity).getText();
    }

    // Get the name of a specific item
    public String getItemName(WebElement item) {
        return item.findElement(itemName).getText().trim();
    }

    // Get the price of a specific item
    public String getItemPrice(WebElement item) {
        return item.findElement(itemPrice).getText();
    }

    // Remove a specific item from the cart
    public void removeItem(WebElement item) {
        WebElement removeBtn = driver.findElement(removeButton);
        scrollToElement(removeBtn);
        removeBtn.click();
    }

    // Click 'Continue Shopping'
    public void clickContinueShopping() {
        WebElement continueShoppingBtn = driver.findElement(continueShoppingButton);
        scrollToElement(continueShoppingBtn);
        continueShoppingBtn.click();
    }

    // Click 'Checkout'
    public void clickCheckout() {
        WebElement checkoutBtn = driver.findElement(checkoutButton);
        scrollToElement(checkoutBtn);
        checkoutBtn.click();
    }

    // Verify if the item is in the cart
    public boolean isItemInCart(String expectedItemName) {
        // System.out.println("Checking for item: " + expectedItemName);
        List<WebElement> itemsInCart = getCartItems();
        // System.out.println("Number of items in cart: " + itemsInCart.size());
        for (WebElement item : itemsInCart) {
            String actualItemName = getItemName(item);
            // System.out.println("Found item in cart: " + actualItemName);
            if (actualItemName.equalsIgnoreCase(expectedItemName)) {
                return true;
            }
        }
        return false;
    }

    // Verify the total number of items in the cart
    public int getNumberOfItemsInCart() {
        return getCartItems().size();
    }
}
=======
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private By cartItems = By.cssSelector("[data-test='inventory-item']");
    private By itemQuantity = By.cssSelector("[data-test='item-quantity']");
    private By itemName = By.cssSelector("[data-test='inventory-item-name']");
    private By itemPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By removeButton = By.cssSelector("[class='btn btn_secondary btn_small cart_button']");
    private By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");
    private By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Get list of all items in the cart
    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    // Get the quantity of a specific item
    public String getItemQuantity(WebElement item) {
        return item.findElement(itemQuantity).getText();
    }

    // Get the name of a specific item
    public String getItemName(WebElement item) {
        return item.findElement(itemName).getText().trim();
    }

    // Get the price of a specific item
    public String getItemPrice(WebElement item) {
        return item.findElement(itemPrice).getText();
    }

    // Remove a specific item from the cart
    public void removeItem(WebElement item) {
        WebElement removeBtn = driver.findElement(removeButton);
        scrollToElement(removeBtn);
        removeBtn.click();
    }

    // Click 'Continue Shopping'
    public void clickContinueShopping() {
        WebElement continueShoppingBtn = driver.findElement(continueShoppingButton);
        scrollToElement(continueShoppingBtn);
        continueShoppingBtn.click();
    }

    // Click 'Checkout'
    public void clickCheckout() {
        WebElement checkoutBtn = driver.findElement(checkoutButton);
        scrollToElement(checkoutBtn);
        checkoutBtn.click();
    }

    // Verify if the item is in the cart
    public boolean isItemInCart(String expectedItemName) {
        // System.out.println("Checking for item: " + expectedItemName);
        List<WebElement> itemsInCart = getCartItems();
        // System.out.println("Number of items in cart: " + itemsInCart.size());
        for (WebElement item : itemsInCart) {
            String actualItemName = getItemName(item);
            // System.out.println("Found item in cart: " + actualItemName);
            if (actualItemName.equalsIgnoreCase(expectedItemName)) {
                return true;
            }
        }
        return false;
    }

    // Verify the total number of items in the cart
    public int getNumberOfItemsInCart() {
        return getCartItems().size();
    }
}
>>>>>>> 85d468a (Upload project files with src and test folders)
