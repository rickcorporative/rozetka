package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout {
    private WebDriver driver;
    private By last = By.cssSelector("input.button.ng-valid.ng-touched");

    public Checkout(WebDriver driver){
        this.driver = driver;
    }

    public void waitForPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(last)));
    }
}
