package Book;

import common.BaseAPIs;

/**
 * Created by sami on 8/8/17.
 */
public class BookFlightOnlyTestScript extends BaseAPIs {


    public void ScreenShotOnFail() throws InterruptedException {

        clickById("flightOnlyWidgetOptions");
        sleepFor(3);
        //verify roundtrip selection is selectd
        verifyRadioButtonSelection("journeyRoundTrip");
        //printing all departure city options
        printListOfWebElementsByID("departCityCodeSelect");
        //user selects a departure city
        selectOptionByVisibleText("departCityCodeSelect","Atlanta, GA (ATL)");
        //user selects arrival city
        selectOptionByVisibleText("destCityCodeSelect","Aguadilla, Puerto Rico (BQN)");
        //printing all arrival city options
        printListOfWebElementsByID("destCityCodeSelect");
        //verify departure text field exists
        verifyTextFieldisDisplayed("departDate");
        //click and send keys to departure date text field
        clickById("departDate");
        typeByID("departDate", "10/25/2017");
        //click and send keys to return date field
        clickById("returnDate");
        typeByID("returnDate", "11/30/2017");
        //print all options in adult dropdown list
        printListOfWebElementsByID("paxAdults");
        //select 4 adults
        selectOptionByVisibleText("paxAdults","4 adults");
        //print all options in children dropdown list
        printListOfWebElementsByID("paxMinors");
        //select 1 child from drop down list
        selectOptionByVisibleText("paxMinors", "0 children");
        //verify that Search Flights Button is present
        verifyButtonIsPresent(".//button[@class='button primary secondary flightSearch']", "(Pass) Search Flight Button is present", "(Fail) Search Button is not Present");
        sleepFor(4);
        //click search flights button
        clickByXpath(".//button[@class='button primary secondary flightSearch']");

        //switching to main home window
        goBackToHomeWindow();


        sleepFor(10);

        //verify that the button doesnot appear before entering credentials
        verifyButtonIsPresent(".//button[@class='button primary']","(fail) Appears and is present","(pass) supposed to not appear and is not present");



        //selecting month of young traveler
        selectOptionByVisibleText("child_0_month", "April");

        //selecting day of young traveler
        selectOptionByVisibleText("child_0_day", "4");

        //selecting year of young traveler
        selectOptionByVisibleText("child_0_year", "2003");

        //click continue button
        clickByXpath("//button[@class='button primary']");

        //take screen shot on failure








        getCurrentPageUrl();
    }



}



