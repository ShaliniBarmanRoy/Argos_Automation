package stepdefinitions;

import com.pages.productPage;
import com.pages.trolleyPage;
import com.utls.driverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class trolleyPageStep
{
    WebDriver driver;
    productPage prPage = new productPage(driverFactory.getDriver());
    trolleyPage tPage = new trolleyPage(driverFactory.getDriver());


    @And("the user validates the details of the product added in the basket")
    public void theUserValidatesTheDetailsOfTheProductAddedInTheBasket() throws IOException, InvalidFormatException {
        tPage.validateTheProductDetailsInBasket();
    }
    @When("the user increase the quantity to {string} in the basket")
    public void theUserIncreaseTheQuantityToInTheBasket(String quantity) throws IOException, InvalidFormatException {
        tPage.increaseTheQuantityOfTheProduct(quantity);
    }


    @Then("the user validates the subtotal is updated")
    public void theUserValidatesTheSubtotalIsUpdated() throws IOException, InvalidFormatException {
        tPage.validatesTheSubtotalIsUpdated();
    }


}

