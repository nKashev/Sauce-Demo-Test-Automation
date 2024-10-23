package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonElements extends BasePage {

    // Elements for header and menu
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartContainer;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    public CommonElements(WebDriver driver) {
        super(driver);
    }

    // Method to click the burger menu
    public void openMenu() {
        menuButton.click();
    }

    // Navigate to the cart
    public void goToCart() {
        cartButton.click();
    }

    // Method to get the number of items in the cart
    public String getCartItemCount() {
        return cartContainer.getText();
    }

    // Method to log out
    public void logout() {
        openMenu();

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

        // System.out.println("Is logout button displayed? " + logoutButton.isDisplayed());
        // System.out.println("Is logout button enabled? " + logoutButton.isEnabled());

        scrollToElement(logoutButton);

        logoutButton.click();
    }   

    // Method to verify if on Login page
    public boolean isLoginButtonDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.isLoginButtonDisplayed();
    }
}
