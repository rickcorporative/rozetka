/*
 * File: CartPage.java
 * Author: Yaroslav
 * Date: 30.12.2024
 * Description: Page Object of cart
 */
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By bullets = By.cssSelector("button.button.menu-toggle-btn");
    private By deleteBtn = By.cssSelector("div#cartProductActions0");
    private List<String> cartItems;

    public CartPage(WebDriver driver){
        this.driver = driver;
        cartItems = new ArrayList<>();
    }

    public void addProduct(String product){
        cartItems.add(product);
    }

    public void removeProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement bulletBtn = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(bullets)));

        bulletBtn.click();
        cartItems.remove(0);

        WebDriverWait waitForDelete = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(deleteBtn)));
        deleteButton.click();
    }

    public boolean isProductInCart(String product) {
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println("Корзина пуста");
            return false;
        }
        for (String element : cartItems) {
            if (element.toLowerCase().contains(product.toLowerCase())) {
                System.out.println("Це вже є в корзині");
                return true;
            }
        }
        return false;
    }

    public boolean isCartEmpty(){
        if(!cartItems.isEmpty()){
            System.out.println("В корзині щось залишилось");
            return false;
        }
        System.out.println("Корзина пуста");
        return true;
    }
}
