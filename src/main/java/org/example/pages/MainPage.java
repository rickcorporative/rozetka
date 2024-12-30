/*
 * File: MainPage.java
 * Author: Yaroslav
 * Date: 30.12.2024
 * Description: Page Object of rozetka`s main page
 */
package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    private WebDriver driver;
    private By searchBox = By.name("search"); // Локатор поля поиска
    private By searchButton = By.cssSelector("button.button_color_green.button_size_medium.search-form__submit"); // Локатор кнопки поиска
    private By cartBtn = By.cssSelector("button.header-cart__button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://rozetka.com.ua/");
    }

    public void searchProduct(String productName) {
        try {
            Thread.sleep(3000); // Пауза 3 секунды, чтобы страница прогрузилась
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public WebElement getFirstProduct () {
        WebElement firstProduct = driver.findElements(By.cssSelector(".goods-tile__title.ng-star-inserted")).get(0);

        return firstProduct;
    }

    public void openCart(){
        driver.findElement(cartBtn).click();
    }




}