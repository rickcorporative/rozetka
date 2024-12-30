/*
 * File: ProductPage.java
 * Author: Yaroslav
 * Date: 30.12.2024
 * Description: Page Object of product
 */
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver driver;
    private By buyBtn = By.cssSelector(".buy-button.button.ng-star-inserted");
    private By addToCartBtn = By.cssSelector("a.button.cart-receipt__submit");
    private By productTitle = By.cssSelector("h1.title__font.ng-star-inserted");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }


    public void addToCart(){
        driver.findElement(buyBtn).click();
        try {
            Thread.sleep(3000); // Пауза 3 секунды, чтобы страница прогрузилась
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(addToCartBtn).click();

        try {
            Thread.sleep(3000); // Пауза 3 секунды, чтобы страница прогрузилась
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentTitle(){
        WebElement currentTitle =  driver.findElement(productTitle);

        return currentTitle.getText();
    }
}
