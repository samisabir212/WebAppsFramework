package Registration;

import DBdataSource.Datasource;
import commonAPI_1.BaseAPIs;
import org.testng.annotations.Test;

import java.sql.SQLException;


/**
 * Created by sami on 7/19/17.
 */
public class DBDDL extends BaseAPIs {





    @Test
    public void TestDropDownListDB() throws InterruptedException, SQLException {

        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("cant open datasource");
            return;
        }

        datasource.open();

        //click Registration page link
        clickById("menu-item-374");


        //print list of all items in Country dropdown list
        printListOfWebElementsByID("dropdown_7");


        //get all countries listed in country column in demoqa Table from DB
        datasource.printDBcountryList("select country from demoqa;");



        //check if webapp DDL matches DB list data



        sleepFor(5);



        datasource.close();


    }


}
