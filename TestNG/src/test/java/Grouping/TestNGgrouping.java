package Grouping;

import commonAPI_1.BaseAPIs;
import org.testng.annotations.Test;

/**
 * Created by sami on 8/3/17.
 */
public class TestNGgrouping extends BaseAPIs {


    @Test(groups = {"enterText"})
    public void firstName_lastNameFields() throws InterruptedException {

        typeByXpath(".//div[@class='control-group']//input[@name='firstname']", "sami");
        typeByID(".//div[@class='control-group']//input[@name='lastname']","sabir-idrissi");

        sleepFor(5);

    }

    @Test(groups = {"enterText"})
    public void datefield() {


        typeByID("datepicker", "10/25/1989");


    }


    @Test(groups = {"buttons"})
    public void radiobuttons() {

        clickById("sex-0");
        clickById("exp-1");
    }


    @Test(groups = {"buttons"})
    public void checkbuttons() {

        clickById("tool-0");
        clickById("tool-1");


    }



}
