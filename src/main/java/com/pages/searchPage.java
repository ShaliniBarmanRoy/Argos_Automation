package com.pages;
import com.utls.driverFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utls.baseActions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.utls.baseActions.captureScreenshot;


public class searchPage {

    WebDriver driver;
    baseActions ba = new baseActions();

    public searchPage(WebDriver driver)
    {
        this.driver = driver;
    }


    By productCategoryDisplayed = By.xpath("//div[@data-el = 'search-title']//h1");
    By productDetails = By.xpath("//div[@data-test='product-group-card-0']//div[2]//div[@data-test='component-product-card-textContainer']//a[2]//div");


    public static String prodname;
    public void validateTheSearchResultsDisplayedBasedOnProduct(String product)
    {
        prodname= product;
        String productOutput = driver.findElement(productCategoryDisplayed).getText().toLowerCase();
        boolean flag = productOutput.contains(product);
        Assert.assertTrue(flag,"The  search result page is not displaying '"+product+"' details");

    }


    public void openTheProductFromSearchPage() throws IOException, InvalidFormatException {
        String productName = driver.findElement(productDetails).getText();
        System.out.println(productName);
        ba.clickOnElement(productDetails,driver);
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
         captureScreenshot(driver,docName);

    }
}
