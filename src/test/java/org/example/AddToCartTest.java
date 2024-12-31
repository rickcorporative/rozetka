/*
 * File: AddToCartTest.java
 * Author: Yaroslav
 * Date: 30.12.2024
 * Description: Test class for work with pages
 */
package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.CartPage;
import org.example.pages.Checkout;
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
import java.util.List;

public class AddToCartTest {
    //поля
    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private Checkout checkoutPage;
    private String searchQuery = "iPhone 16";
    private List<String> cartItems;

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
        checkoutPage = new Checkout(driver);
    }

    //початок тесту
    @Test
    public void testProductSearchAndCartManagement() {
        //opening main page
        mainPage.open();

        mainPage.searchProduct(searchQuery);

        //check if there is similar product in cart already
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println("Корзина пуста");
        }

        for (String element : cartItems) {
            if (element.toLowerCase().contains(searchQuery.toLowerCase())) {
                System.out.println("Це вже є в корзині");
            }
        }


        //Loading
        mainPage.waitProductListLoaded();
        //clicking on the first product


        mainPage.clickFirstProduct();


        //adding product to the state of cart
        this.cartItems.add(productPage.getCurrentTitle());

        //again checking
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println("Корзина пуста");
        }

        for (String element : cartItems) {
            if (element.toLowerCase().contains(productPage.getCurrentTitle().toLowerCase())) {
                System.out.println("Це вже є в корзині");
            }
        }
        //click on btn
        productPage.addToCart();

        //waiting till
        checkoutPage.waitForPageLoaded();

        //going back to main page
        mainPage.open();

        //opening cart modal
        mainPage.openCart();


        cartPage.removeProduct();
        this.cartItems.remove(0);

        //checking if the cart is empty
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println("Корзина пуста");
        }
    }
    //the end
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
