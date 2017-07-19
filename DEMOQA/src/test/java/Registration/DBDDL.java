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

        sleepFor(5);



        datasource.close();


    }


}
