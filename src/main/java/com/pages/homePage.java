package com.pages;

import com.utls.driverFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utls.baseActions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.utls.baseActions.captureScreenshot;

public class homePage
{
    WebDriver driver;
    baseActions ba = new baseActions();
    public homePage(WebDriver driver)
    {
        this.driver = driver;
    }


    By acceptCookies_button = By.xpath("//button[text()='Continue and accept']");
    By argos_logo = By.xpath("//img[@src='https://media.4rgos.it/i/Argos/logo_argos2x?w=120&h=103&qlt=75&fmt=png']");

    By searchBox = By.xpath("//input[@placeholder ='Search products, brands or FAQs']");
    By searchButton = By.xpath("//button//span[text()='Search']");



    public void launchTheApplication(String baseUrl) throws IOException, InvalidFormatException {
//        driver = driverFactory.initializeDriver(ba.getProperty("browser"));
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ba.clickOnElement(acceptCookies_button,driver);
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
        captureScreenshot(driver,docName);



    }

    public void validateTheLogoOftheWebsite()
    {
        ba.validateElementPresent(argos_logo,driver);

    }


    public void searchForTheRequiredProduct(String product) throws IOException, InvalidFormatException {
      ba.enterText(product,searchBox,driver);
      ba.clickOnElement(searchButton,driver);
        String docName = "SearchAndCheckProductPrice_"+ba.currentDateTime();
      captureScreenshot(driver,docName);



    }
}