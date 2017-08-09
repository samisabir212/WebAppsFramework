package Book;

import common.BaseAPIs;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by sami on 8/8/17.
 */
public class BookFlightOnly_Objects extends BaseAPIs {

    //to click flights option
    @FindBy(how = How.ID, using = "flightOnlyWidgetOptions")
    public static WebElement flight;

    //to verify show all exists
    @FindBy(how = How.CSS, using = ".show_all")
    public static WebElement showAllButton;

    //click radio button or assure is or not selected
    public static WebElement roundTrip;

    //click radio button or assure is or not selected
    public static WebElement oneWay;

    //click radio button or assure is or not selected
    public static WebElement multiCity;

    public static WebElement departDateField;

    public static WebElement departeDateField_Img;

    public static WebElement adultsDropDown;

    public static WebElement childrenDropDown;

    public static WebElement promoCode;

    public static WebElement freeSpiritMilesRadioButton;

    public static WebElement pfwFreeSpiritMilesText;









}



