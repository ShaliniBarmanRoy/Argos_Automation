package hooks;

import com.utls.baseActions;
import com.utls.driverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class SetUpHooks
{
    WebDriver driver;
    baseActions ba = new baseActions();

    @Before
    public void setUp() {
        // Initialize the WebDriver using DriverFactory

        driver = driverFactory.initializeDriver(ba.getProperty("browser"));

    }
    @After
    public void tearDown() {
        // Quit the WebDriver using DriverFactory
        driverFactory.quitDriver();
    }

}
