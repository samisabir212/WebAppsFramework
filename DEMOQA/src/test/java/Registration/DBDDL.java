package Registration;

import DBdataSource.Datasource;
import common.BaseAPIs;
import org.testng.annotations.Test;

import java.sql.Statement;

/**
 * Created by sami on 7/19/17.
 */
public class DBDDL extends BaseAPIs {





    @Test
    public void TestDropDownListDB() throws InterruptedException {

        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("cant open datasource");
            return;
        }

        datasource.open();

        //click Registration page link
        clickById("menu-item-374");


        //get list of all items in Country dropdown list


        //get all countries listed in country column in demoqa Table from DB


        //assert whether all expected data equals actual data

        sleepFor(5);



        datasource.close();


    }


}
