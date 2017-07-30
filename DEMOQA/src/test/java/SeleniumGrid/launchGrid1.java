package SeleniumGrid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by sami on 7/25/17.
 */
public class launchGrid1 extends GridBaseAPI {
    private WebDriver driver;
    private String baseUrl;
    private String nodeURL;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {

    }

    @Test
    public void searchFlights() throws Exception {
        search.clickFlightsTab();
        search.setOriginCity("New York");
        search.setDestinationCity("San Francisco");
        search.setDepartureDate("10/28/2015");
        search.setReturnDate("10/31/2015");
    }

    @AfterClass
    public void tearDown() throws Exception {
    }

}



