package stepdefinitions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import com.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import com.utls.*;

import java.io.IOException;

public class homePageStep
{
    WebDriver driver;
    homePage hPage = new homePage(driverFactory.getDriver());
    baseActions ba = new baseActions();



    @Given("the user launches the argos website")
    public void theUserLaunchesTheArgosWebsite() throws IOException, InvalidFormatException {
        hPage.launchTheApplication(ba.getProperty("baseUrl"));
    }
    @And("the user validates the correct website is displayed")
    public void theUserValidatesTheCorrectWebsiteIsDisplayed()
    {

        hPage.validateTheLogoOftheWebsite();
    }

    @When("the user searches for a product {string} in searchbox")
    public void theUserSearchesForAProductInSearchbox(String product) throws IOException, InvalidFormatException {
        hPage.searchForTheRequiredProduct(product);
    }
//    @After
//    public void tearDown() {
//        // Quit the WebDriver using DriverFactory
//        driverFactory.quitDriver();
//    }



}
