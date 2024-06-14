package stepdefinitions;

import com.pages.homePage;
import com.pages.productPage;
import com.pages.searchPage;
import com.utls.baseActions;
import com.utls.driverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class productPageStep {


    WebDriver driver;
    productPage prPage = new productPage(driverFactory.getDriver());



    @And("the user add the product to the basket")
    public void theUserAddTheProductToTheBasket() throws IOException, InvalidFormatException {
        prPage.addTheProductToTrolley();
    }
}
