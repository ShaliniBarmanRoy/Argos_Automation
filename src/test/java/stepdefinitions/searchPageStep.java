package stepdefinitions;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import com.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import com.utls.*;

import java.io.IOException;

public class searchPageStep {

    WebDriver driver;
    homePage hPage;
    searchPage sPage = new searchPage(driverFactory.getDriver());
    baseActions ba = new baseActions();



    @Then("the user verifies the correct search results of the {string} is displayed")
    public void theUserVerifiesTheCorrectSearchResultsOfTheIsDisplayed(String product)
    {

        sPage.validateTheSearchResultsDisplayedBasedOnProduct(product);
    }

    @And("the user opens the product  from the search result")
    public void theUserOpensTheProductFromTheSearchResult() throws IOException, InvalidFormatException {
        sPage.openTheProductFromSearchPage();
   }
}
