package com.pages;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utls.baseActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.utls.baseActions.captureScreenshot;


public class productPage
{
    WebDriver driver;
    baseActions ba = new baseActions();

    public productPage(WebDriver driver)
    {
        this.driver = driver;
    }

    By prodName = By.xpath("//span[@data-test='product-title']");
    By prodPrice = By.xpath("//li[@itemprop= 'price']//h2");
    By addtoTrolley_button = By.xpath("//button//span[text()='Add']");
    By continue_button = By.xpath("//div//a[text()='Continue without insurance']");
    By gotoTrolley_button = By.xpath("//a[text()='Go to trolley']");


    public static String ProductN;
    public static String ProductP;
    public void addTheProductToTrolley() throws IOException, InvalidFormatException {
//       ba.validateElementPresent(prodName,driver);
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(prodName));

        ProductN = driver.findElement(prodName).getText();
        System.out.println(ProductN);
        ProductP= driver.findElement(prodPrice).getText();
        System.out.println(ProductP);
        ba.clickOnElement(addtoTrolley_button,driver);
        //validate if continue without insurance button is present
        try{
            ba.validateElementPresent(continue_button,driver);
            ba.clickOnElement(continue_button,driver);
        }
        //if not present click on add to Trolley
        catch (Exception e)
        {
            ba.clickOnElement(gotoTrolley_button,driver);
        }
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
        captureScreenshot(driver,docName);

    }
}
