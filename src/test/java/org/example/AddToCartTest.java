/*
 * File: AddToCartTest.java
 * Author: Yaroslav
 * Date: 30.12.2024
 * Description: Test class for work with pages
 */
package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.CartPage;
import org.example.pages.MainPage;
import org.example.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest {
    //поля
    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private String searchQuery = "iPhone 16";

    // настройка перед тестом
    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    //початок тесту
    @Test
    public void testProductSearchAndCartManagement() {
        //opening main page
        mainPage.open();

        mainPage.searchProduct(searchQuery);

        //check if there is similar product in cart already
        cartPage.isProductInCart(searchQuery);

        //Loading
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section.content_type_catalog")));

        //clicking on the first product
        mainPage.getFirstProduct().click();

        //Loading
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //adding product to the state of cart
        cartPage.addProduct(productPage.getCurrentTitle());
        //again checking
        cartPage.isProductInCart(productPage.getCurrentTitle());
        //click on btn
        productPage.addToCart();


        //loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //going back to main page
        mainPage.open();
        //loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //opening cart modal
        mainPage.openCart();
        //loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cartPage.removeProduct();

        cartPage.isCartEmpty();
    }
    //the end
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
