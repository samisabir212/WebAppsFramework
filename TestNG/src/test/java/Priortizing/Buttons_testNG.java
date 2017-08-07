package Priortizing;

import common.BaseAPIs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by sami on 8/2/17.
 */
public class Buttons_testNG extends BaseAPIs {


    @Test(priority = 0, enabled = false)
    public void ClickAllButtons() {


        for (int i = 0; i <= 10; i++) {

            List<WebElement> buttons = driver.findElements(By.xpath(".//div[@class='et_pb_button_module_wrapper et_pb_module']"));

            buttons.get(i).click();
            System.out.println("button " + i + " Clicked");


        }

    }

    @Test(priority = 1, enabled = true)
    public void twitterLink1() {

        clickByXpath(".//div[@id='page-container']//div[@id='et-main-area']//li[@class='et_pb_social_icon et_pb_social_network_link et-social-twitter et_pb_social_media_follow_network_0']");


        getCurrentPageUrl();


    }





    }
