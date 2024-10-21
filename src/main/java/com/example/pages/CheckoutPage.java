<<<<<<< HEAD
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Step One: Checkout Form
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");

    // Error Message Locator
    private By errorMessage = By.xpath("//div[@class='error-message-container error']/h3");

    // Step Two: Order Summary
    private By itemTotalLabel = By.className("summary_subtotal_label");
    private By taxLabel = By.className("summary_tax_label");
    private By totalLabel = By.className("summary_total_label");
    private By finishButton = By.id("finish");

    // Step Three: Order Completion
    private By successHeader = By.className("complete-header");
    private By successText = By.className("complete-text");
    private By backToProductsButton = By.id("back-to-products");

    // Item details on the order summary
    private By cartItemNames = By.className("inventory_item_name");
    private By cartItemPrices = By.className("inventory_item_price");
    private By cartItemDescriptions = By.className("inventory_item_desc");

    // Step One Methods
    public void fillFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void fillPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        WebElement continueBtn = driver.findElement(continueButton);
        scrollToElement(continueBtn);
        continueBtn.click();
    }

    public void clickCancel() {
        WebElement cancelBtn = driver.findElement(cancelButton);
        scrollToElement(cancelBtn);
        cancelBtn.click();
    }

    // Method to fill in and submit the form
    public void submitForm(String firstName, String lastName, String postalCode) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPostalCode(postalCode);
        clickContinue();
    }

    // Method to check if error message is displayed
    public boolean hasErrorMessage() {
        return driver.findElements(errorMessage).size() > 0;
    }

    // Method to verify that the information on the last preview screen is correct
    public boolean isInformationCorrect(List<String> expectedItemNames, List<String> expectedItemDescriptions, List<Double> expectedItemPrices) {
        return checkItemDetails(expectedItemNames, expectedItemDescriptions, expectedItemPrices) &&
               checkButtonsVisibility() &&
               checkTotalPrice(expectedItemPrices);
    }

    // Method to check item names, descriptions, and prices
    public boolean checkItemDetails(List<String> expectedItemNames, List<String> expectedItemDescriptions, List<Double> expectedItemPrices) {
        List<WebElement> itemNames = driver.findElements(cartItemNames);
        List<WebElement> itemPrices = driver.findElements(cartItemPrices);
        List<WebElement> itemDescriptions = driver.findElements(cartItemDescriptions);

        // Verify the count matches
        if (itemNames.size() != expectedItemNames.size() || itemPrices.size() != expectedItemPrices.size() || itemDescriptions.size() != expectedItemDescriptions.size()) {
            return false;
        }

        for (int i = 0; i < expectedItemNames.size(); i++) {
            if (!itemNames.get(i).getText().equals(expectedItemNames.get(i)) ||
                !itemPrices.get(i).getText().equals("$" + expectedItemPrices.get(i)) ||
                !itemDescriptions.get(i).getText().equals(expectedItemDescriptions.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the buttons are visible
    public boolean checkButtonsVisibility() {
        boolean isCancelButtonPresent = driver.findElement(cancelButton).isDisplayed();
        boolean isFinishButtonPresent = driver.findElement(finishButton).isDisplayed();
        return isCancelButtonPresent && isFinishButtonPresent;
    }

    // Method to check if the total price is correct
    public boolean checkTotalPrice(List<Double> expectedItemPrices) {
        double expectedTotalPrice = expectedItemPrices.stream().mapToDouble(Double::doubleValue).sum();
        String actualTotalPriceText = driver.findElement(itemTotalLabel).getText();
        double actualTotalPrice = parsePriceFromText(actualTotalPriceText);
        
        return actualTotalPrice == expectedTotalPrice;
    }

    // Helper method to parse the price from the text
    private double parsePriceFromText(String priceText) {
        String numericPart = priceText.replaceAll("[^0-9.]", "").trim();
        try {
            return Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing price: " + priceText);
            return 0.0; // or handle the error as needed
        }
    }
    

    // Step Two Methods
    public String getItemTotal() {
        return driver.findElement(itemTotalLabel).getText();
    }

    public String getTax() {
        return driver.findElement(taxLabel).getText();
    }

    public String getTotal() {
        return driver.findElement(totalLabel).getText();
    }

    public void clickFinish() {
        WebElement finishBtn = driver.findElement(finishButton);
        scrollToElement(finishBtn);
        finishBtn.click();
    }

    // Step Three: Completion Methods
    public String getSuccessHeader() {
        return driver.findElement(successHeader).getText().trim();
    }

    public String getSuccessText() {
        return driver.findElement(successText).getText().trim();
    }

    public void clickBackHome() {
        WebElement backHomeButton = driver.findElement(backToProductsButton);
        scrollToElement(backHomeButton);
        backHomeButton.click();
    }

    // Method to check if success message is displayed
    public boolean hasSuccessMessage(String expectedHeader, String expectedText) {
        try {
            String actualHeader = getSuccessHeader();
            String actualText = getSuccessText();
    
            // Print actual values for debugging
            // System.out.println("Expected Header: " + expectedHeader);
            // System.out.println("Actual Header: " + actualHeader);
            // System.out.println("Expected Text: " + expectedText);
            // System.out.println("Actual Text: " + actualText);
    
            return actualHeader.equals(expectedHeader) && actualText.equals(expectedText);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Additional methods to handle items in cart if needed
    public boolean isItemInCart(String itemName) {
        return driver.findElements(By.xpath("//div[text()='" + itemName + "']")).size() > 0;
    }

    public String getItemPrice(String itemName) {
        return driver.findElement(By.xpath("//div[text()='" + itemName + "']/following-sibling::div[contains(@class, 'item_price')]")).getText();
    }
}
=======
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Step One: Checkout Form
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");

    // Error Message Locator
    private By errorMessage = By.xpath("//div[@class='error-message-container error']/h3");

    // Step Two: Order Summary
    private By itemTotalLabel = By.className("summary_subtotal_label");
    private By taxLabel = By.className("summary_tax_label");
    private By totalLabel = By.className("summary_total_label");
    private By finishButton = By.id("finish");

    // Step Three: Order Completion
    private By successHeader = By.className("complete-header");
    private By successText = By.className("complete-text");
    private By backToProductsButton = By.id("back-to-products");

    // Item details on the order summary
    private By cartItemNames = By.className("inventory_item_name");
    private By cartItemPrices = By.className("inventory_item_price");
    private By cartItemDescriptions = By.className("inventory_item_desc");

    // Step One Methods
    public void fillFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void fillPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        WebElement continueBtn = driver.findElement(continueButton);
        scrollToElement(continueBtn);
        continueBtn.click();
    }

    public void clickCancel() {
        WebElement cancelBtn = driver.findElement(cancelButton);
        scrollToElement(cancelBtn);
        cancelBtn.click();
    }

    // Method to fill in and submit the form
    public void submitForm(String firstName, String lastName, String postalCode) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPostalCode(postalCode);
        clickContinue();
    }

    // Method to check if error message is displayed
    public boolean hasErrorMessage() {
        return driver.findElements(errorMessage).size() > 0;
    }

    // Method to verify that the information on the last preview screen is correct
    public boolean isInformationCorrect(List<String> expectedItemNames, List<String> expectedItemDescriptions, List<Double> expectedItemPrices) {
        return checkItemDetails(expectedItemNames, expectedItemDescriptions, expectedItemPrices) &&
               checkButtonsVisibility() &&
               checkTotalPrice(expectedItemPrices);
    }

    // Method to check item names, descriptions, and prices
    public boolean checkItemDetails(List<String> expectedItemNames, List<String> expectedItemDescriptions, List<Double> expectedItemPrices) {
        List<WebElement> itemNames = driver.findElements(cartItemNames);
        List<WebElement> itemPrices = driver.findElements(cartItemPrices);
        List<WebElement> itemDescriptions = driver.findElements(cartItemDescriptions);

        // Verify the count matches
        if (itemNames.size() != expectedItemNames.size() || itemPrices.size() != expectedItemPrices.size() || itemDescriptions.size() != expectedItemDescriptions.size()) {
            return false;
        }

        for (int i = 0; i < expectedItemNames.size(); i++) {
            if (!itemNames.get(i).getText().equals(expectedItemNames.get(i)) ||
                !itemPrices.get(i).getText().equals("$" + expectedItemPrices.get(i)) ||
                !itemDescriptions.get(i).getText().equals(expectedItemDescriptions.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the buttons are visible
    public boolean checkButtonsVisibility() {
        boolean isCancelButtonPresent = driver.findElement(cancelButton).isDisplayed();
        boolean isFinishButtonPresent = driver.findElement(finishButton).isDisplayed();
        return isCancelButtonPresent && isFinishButtonPresent;
    }

    // Method to check if the total price is correct
    public boolean checkTotalPrice(List<Double> expectedItemPrices) {
        double expectedTotalPrice = expectedItemPrices.stream().mapToDouble(Double::doubleValue).sum();
        String actualTotalPriceText = driver.findElement(itemTotalLabel).getText();
        double actualTotalPrice = parsePriceFromText(actualTotalPriceText);
        
        return actualTotalPrice == expectedTotalPrice;
    }

    // Helper method to parse the price from the text
    private double parsePriceFromText(String priceText) {
        String numericPart = priceText.replaceAll("[^0-9.]", "").trim();
        try {
            return Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing price: " + priceText);
            return 0.0; // or handle the error as needed
        }
    }
    

    // Step Two Methods
    public String getItemTotal() {
        return driver.findElement(itemTotalLabel).getText();
    }

    public String getTax() {
        return driver.findElement(taxLabel).getText();
    }

    public String getTotal() {
        return driver.findElement(totalLabel).getText();
    }

    public void clickFinish() {
        WebElement finishBtn = driver.findElement(finishButton);
        scrollToElement(finishBtn);
        finishBtn.click();
    }

    // Step Three: Completion Methods
    public String getSuccessHeader() {
        return driver.findElement(successHeader).getText().trim();
    }

    public String getSuccessText() {
        return driver.findElement(successText).getText().trim();
    }

    public void clickBackHome() {
        WebElement backHomeButton = driver.findElement(backToProductsButton);
        scrollToElement(backHomeButton);
        backHomeButton.click();
    }

    // Method to check if success message is displayed
    public boolean hasSuccessMessage(String expectedHeader, String expectedText) {
        try {
            String actualHeader = getSuccessHeader();
            String actualText = getSuccessText();
    
            // Print actual values for debugging
            // System.out.println("Expected Header: " + expectedHeader);
            // System.out.println("Actual Header: " + actualHeader);
            // System.out.println("Expected Text: " + expectedText);
            // System.out.println("Actual Text: " + actualText);
    
            return actualHeader.equals(expectedHeader) && actualText.equals(expectedText);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Additional methods to handle items in cart if needed
    public boolean isItemInCart(String itemName) {
        return driver.findElements(By.xpath("//div[text()='" + itemName + "']")).size() > 0;
    }

    public String getItemPrice(String itemName) {
        return driver.findElement(By.xpath("//div[text()='" + itemName + "']/following-sibling::div[contains(@class, 'item_price')]")).getText();
    }
}
>>>>>>> 85d468a (Upload project files with src and test folders)
