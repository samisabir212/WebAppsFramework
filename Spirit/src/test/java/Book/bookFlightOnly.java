package Book;

import common.BaseAPIs;
import org.testng.annotations.Test;


/**
 * Created by sami on 8/8/17.
 */
public class bookFlightOnly extends BaseAPIs {

    BookFlightOnly_Objects bookFlightOnly_objects = new BookFlightOnly_Objects();


    //user should be able to search a Round trip flight
    @Test
    public void searchFlights() throws InterruptedException {

        clickById("flightOnlyWidgetOptions");

        sleepFor(3);

        //verify roundtrip selection is selectd
        verifyRadioButtonSelection("journeyRoundTrip");

        //printing all departure city options
        printListOfWebElementsByID("departCityCodeSelect");

        //user selects a departure city


        //user selects arrival city


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


        //

        sleepFor(6);

    }


}
