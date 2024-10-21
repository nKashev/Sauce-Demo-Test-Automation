<<<<<<< HEAD
package com.example.pages;

import com.example.models.Item;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private List<Item> selectedItems;

    // Elements for products and adding to cart
    @FindBy(css = ".inventory_item")
    private List<WebElement> productItems;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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
                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    
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

    // Method to verify that a specific product is added to the cart by its name
    public boolean areProductsInCart(List<String> selectedItemNames) {
        boolean allProductsInCart = true;
    
        for (String itemName : selectedItemNames) {
            String itemDataTestId = itemName.toLowerCase().replace(" ", "-");
    
            try {
                // System.out.println("Checking for product: " + itemName);
                String removeButtonSelector = "//button[@data-test='remove-" + itemDataTestId + "']";
                // System.out.println("Using XPath: " + removeButtonSelector);
    
                WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(removeButtonSelector)));
    
                scrollToElement(removeButton);
    
                if (!removeButton.isDisplayed()) {
                    allProductsInCart = false;
                }
            } catch (TimeoutException e) {
                // System.out.println("Product not found: " + itemName);
                allProductsInCart = false;
            }
        }
    
        return allProductsInCart;
    }

    // Method to verify that a specific product is not in the cart by its name
    public boolean areProductsNotInCart(List<String> productNames) {
        for (String productName : productNames) {
            String buttonSelector = "//div[@class='inventory_item_name' and text()='" + productName + "']/following-sibling::div[@class='pricebar']//button[contains(text(), 'Remove')]";
            List<WebElement> removeButtons = driver.findElements(By.xpath(buttonSelector));
    
            if (!removeButtons.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
=======
package com.example.pages;

import com.example.models.Item;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private List<Item> selectedItems;

    // Elements for products and adding to cart
    @FindBy(css = ".inventory_item")
    private List<WebElement> productItems;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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
                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    
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

    // Method to verify that a specific product is added to the cart by its name
    public boolean areProductsInCart(List<String> selectedItemNames) {
        boolean allProductsInCart = true;
    
        for (String itemName : selectedItemNames) {
            String itemDataTestId = itemName.toLowerCase().replace(" ", "-");
    
            try {
                // System.out.println("Checking for product: " + itemName);
                String removeButtonSelector = "//button[@data-test='remove-" + itemDataTestId + "']";
                // System.out.println("Using XPath: " + removeButtonSelector);
    
                WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(removeButtonSelector)));
    
                scrollToElement(removeButton);
    
                if (!removeButton.isDisplayed()) {
                    allProductsInCart = false;
                }
            } catch (TimeoutException e) {
                // System.out.println("Product not found: " + itemName);
                allProductsInCart = false;
            }
        }
    
        return allProductsInCart;
    }

    // Method to verify that a specific product is not in the cart by its name
    public boolean areProductsNotInCart(List<String> productNames) {
        for (String productName : productNames) {
            String buttonSelector = "//div[@class='inventory_item_name' and text()='" + productName + "']/following-sibling::div[@class='pricebar']//button[contains(text(), 'Remove')]";
            List<WebElement> removeButtons = driver.findElements(By.xpath(buttonSelector));
    
            if (!removeButtons.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
>>>>>>> 85d468a (Upload project files with src and test folders)
