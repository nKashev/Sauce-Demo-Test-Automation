package com.example.pages;

import com.example.models.Item;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private List<Item> selectedItems;

    // Elements for products and adding to cart
    @FindBy(css = ".inventory_item")
    private List<WebElement> productItems;

    public ProductsPage(WebDriver driver) {
        super(driver);
        selectedItems = new ArrayList<>();
    }

    // Method to verify if user is on the Products page by checking the title
    public boolean isOnProductsPage() {
        WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title' and text()='Products']")));
        scrollToElement(productsTitle);
        return productsTitle.isDisplayed();
    }

    // Method to add products to the cart by their names
    public void addProductToCartByName(List<String> productNames) {
        selectedItems = new ArrayList<>();
        String[] itemIds = productNames.stream()
                .map(name -> name.toLowerCase().replace(" ", "-"))
                .toArray(String[]::new);
    
        for (String id : itemIds) {
            try {
                WebElement itemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='inventory_item'][.//button[@data-test='add-to-cart-" + id + "']]")
                ));
    
                // Get item details
                String itemName = itemElement.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
                String itemDesc = itemElement.findElement(By.cssSelector(".inventory_item_desc")).getText().trim();
                String itemPrice = itemElement.findElement(By.cssSelector(".inventory_item_price")).getText().trim();
    
                // Store item details
                selectedItems.add(new Item(itemName, itemDesc, itemPrice));
                // System.out.println("Successfully added: " + itemName);
    
                // Click Add to Cart button
                WebElement addToCartButton = itemElement.findElement(By.cssSelector("button[data-test='add-to-cart-" + id + "']"));
                scrollToElement(addToCartButton);
                wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
            } catch (NoSuchElementException e) {
                // System.out.println("Product not found: " + id);
            } catch (TimeoutException e) {
                // System.out.println("Timeout while adding product: " + id);
            }
        }
        // System.out.println("Selected items after adding: " + selectedItems);
    }

    // Method to return the selectedItems list
    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    // Method to remove a product from the cart by name
    public void removeProductsFromCartByNames(List<String> productNames) {
        for (String productName : productNames) {
            String formattedProductName = productName.toLowerCase().replace(" ", "-");
            String buttonSelector = "//button[@data-test='remove-" + formattedProductName + "']";
    
            try {
                WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(buttonSelector)));
                scrollToElement(removeButton);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonSelector))).click();
    
                // Remove from selectedItems
                selectedItems.removeIf(item -> item.getName().equalsIgnoreCase(productName));
                // System.out.println("Successfully removed: " + productName);
    
                // System.out.println("Selected items after removing: " + selectedItems);
            } catch (NoSuchElementException e) {
                // System.err.println("Remove button not found for product: " + productName);
            } catch (TimeoutException e) {
                // System.err.println("Timed out waiting for remove button for product: " + productName);
            }
        }
    }

    // Improved method to verify that specific products are added to the cart by their names.
    // It waits until the cart badge and the corresponding remove buttons reflect the added products.
    public boolean areProductsInCart(List<String> productNames) {
        try {
            return wait.until(driver -> {
                try {
                    List<WebElement> badgeElements = driver.findElements(By.cssSelector(".shopping_cart_badge"));
                    int badgeCount = 0;
                    if (!badgeElements.isEmpty()) {
                        try {
                            badgeCount = Integer.parseInt(badgeElements.get(0).getText().trim());
                        } catch (NumberFormatException ignored) {
                            badgeCount = 0;
                        }
                    }

                    if (badgeCount < productNames.size()) {
                        return false;
                    }

                    for (String productName : productNames) {
                        String id = productName.toLowerCase().replace(" ", "-");
                        List<WebElement> removeButtons = driver.findElements(By.cssSelector("button[data-test='remove-" + id + "']"));
                        if (removeButtons.isEmpty()) {
                            return false;
                        }
                    }

                    return true;
                } catch (StaleElementReferenceException e) {
                    // DOM re-rendered mid-read; treat as not-ready-yet and let the wait poll again.
                    return false;
                }
            });
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Method to verify that a specific product is not in the cart by its name
    public boolean areProductsNotInCart(List<String> productNames) {
        try {
            return wait.until(driver -> {
                try {
                    for (String productName : productNames) {
                        String id = productName.toLowerCase().replace(" ", "-");
                        List<WebElement> removeButtons = driver.findElements(By.cssSelector("button[data-test='remove-" + id + "']"));
                        if (!removeButtons.isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
}
