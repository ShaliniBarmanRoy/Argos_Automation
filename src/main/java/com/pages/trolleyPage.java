package com.pages;

import com.utls.baseActions;
import com.utls.driverFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.utls.baseActions.captureScreenshot;


public class trolleyPage
{
    WebDriver driver;
    baseActions ba = new baseActions();
productPage prPage = new productPage(driverFactory.getDriver());
    public trolleyPage(WebDriver driver)
    {
        this.driver = driver;
    }

    By quantityDropDown = By.xpath("//label[text()='Quantity']/..//select");
    By productNameD = By.xpath("//div[@class='ProductCardstyles__DetailsContainer-sc-1g8w3q7-7 enFSVU']//a[@data-e2e='product-name']//span");
    By productPrice = By.xpath("//span[@data-e2e='product-line-price']");

    
    public void validateTheProductDetailsInBasket() throws IOException, InvalidFormatException {

        //validate the name of the added product
        String proName = driver.findElement(productNameD).getText();
        System.out.println(proName);
        Assert.assertEquals(proName,prPage.ProductN," The product name is wrong");
        ba.highlightElement(driver.findElement(productNameD),driver);

        //validate the price of the added product
        String proPrice = driver.findElement(productPrice).getText();
        System.out.println(proPrice);
        Assert.assertEquals(proPrice,prPage.ProductP," The product price is wrong");
        ba.highlightElement(driver.findElement(productPrice),driver);
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
       captureScreenshot(driver,docName);


    }

    public static int num;
    public void increaseTheQuantityOfTheProduct(String quantity) throws IOException, InvalidFormatException {
        num = Integer.parseInt(quantity);
        Select selectQuantity = new Select(driver.findElement(quantityDropDown));
        selectQuantity.selectByVisibleText(quantity);
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
     captureScreenshot(driver,docName);


    }

    public void validatesTheSubtotalIsUpdated() throws IOException, InvalidFormatException
    {
        // converting the basic price of the product from String to Integer
        String basicnumericString = prPage.ProductP.replaceAll("[^\\d]", "");
        int basicPrice =Integer.parseInt(basicnumericString);

        // Multiplying the base price with the quantity of the products added

        int expectedTotalPrice = basicPrice*num;


        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(productPrice));

        //converting the updated proce of the product from String to Integer
        String proPrice = driver.findElement(productPrice).getText();
        String updatednumericString = proPrice.replaceAll("[^\\d]", "");
        int actualTotalPrice = Integer.parseInt(updatednumericString);

        //validating the total price with the base price
        Assert.assertEquals(expectedTotalPrice,actualTotalPrice,"The total price is wrong");
        ba.highlightElement(driver.findElement(productPrice),driver);
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
        captureScreenshot(driver,docName);



    }


}
