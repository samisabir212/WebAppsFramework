package commonAPI_1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by EliteBook on 4/25/2017.
 */
public class BaseAPIs {

    public WebDriver driver = null;
    public JavascriptExecutor js;


    /*method for passing the OS parameter and Browser Parameter
       * depending on the passed browser type parameter, it will trigger the respected browser*/
    public WebDriver getLocalDriver(String OS, String browserName) {


        if (browserName.equalsIgnoreCase("chrome")) {
            if (OS.equalsIgnoreCase("Mac")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/src/driver/chromedriver");

            } else if (OS.equalsIgnoreCase("Win10")) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\EliteBook\\Selenium 3.0 2016 batch\\MavenProjects\\WebApp\\Generic\\src\\driver\\chromedriver.exe");
            } else if (OS.equalsIgnoreCase("Linux")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/src/driver/chromedriverLinux");
            }

            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("Mac")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/src/driver/geckodriverMAC");
            } else if (OS.equalsIgnoreCase("Win10")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../Generic/driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;

    }


    //this runs before your test method
    //for cloud service only
    @Parameters({"useCloudEnv", "userName", "accessKey", "os", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("rahmanww") String userName, @Optional("")
            String accessKey, @Optional("Windows 10") String os, @Optional("chrome") String browserName, @Optional("58")
                              String browserVersion, @Optional("http://www.cnn.com") String url) throws IOException {

        //if we are using cloud enviurmment then use it else just get get local driver
        if (useCloudEnv == true) {
            //run in cloud
            getCloudDriver(userName, accessKey, os, browserName, browserVersion);

        } else {
            //run in local
            getLocalDriver(os, browserName);

        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        //driver.manage().window().maximize();

    }


    /*method for Cloud Driver*/
    public WebDriver getCloudDriver(String userName, String accessKey, String os, String browserName,
                                    String browserVersion) throws IOException {
        {

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("platform", os);
            cap.setBrowserName(browserName);
            cap.setCapability("version", browserVersion);
            driver = new RemoteWebDriver(new URL("http://" + userName + ":" + accessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
            return driver;


        }
    }


    @AfterMethod
    public void tearDown() {
        driver.close();

    }

    //************SELECT***********


    public void selectOptionByVisibleText(String locator, String value) {
        WebElement object = driver.findElement(By.id(locator));
        Select select = new Select(object);
        select.selectByVisibleText(value);
    }

    public void clickById(String locator) {
        driver.findElement(By.id(locator)).click();
    }

    //click by xpath
    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    //click by css
    public void clickByCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    /**********TYPE SEND KEYS*********/


    public void typeByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    //typeing by id locator
    public void typeByID(String locator, String value) {
        driver.findElement(By.id(locator)).sendKeys(value);
    }

    //type by id and enter key
    public void typeByIdEnter(String locator, String value) {
        driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
    }


    //type by xpath and ENTER key
    public void typeByXpathEnter(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
    }

    //type by css and ENTER key
    public void typeByCssEnter(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }

    //type by xpath
    public void typeByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    //?????
    public void takeEnterKeys(String locator) {
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }


    public void clearInputField(String locator) {

        driver.findElement(By.cssSelector(locator)).clear();
    }

    public WebElement getElement(String locator, String type) {
        type = type.toLowerCase();
        if (type.equals("id")) {
            System.out.println("Element found with id: " + locator);// you can change it and make it print ID by changing locator to type
            return this.driver.findElement(By.id(locator));
        }
        else if (type.equals("xpath")) {
            System.out.println("Element found with xpath: " + locator);
            return this.driver.findElement(By.xpath(locator));
        }
        else if (type.equals("css")) {
            System.out.println("Element found with xpath: " + locator);
            return this.driver.findElement(By.cssSelector(type));
        }
        else if (type.equals("linktext")) {
            System.out.println("Element found with xpath: " + locator);
            return this.driver.findElement(By.linkText(locator));
        }
        else if (type.equals("partiallinktext")) {
            System.out.println("Element found with xpath: " + locator);
            return this.driver.findElement(By.partialLinkText(type));
        }
        else {
            System.out.println("Locator type not supported");
            return null;
        }
    }

    //get Links
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }


    public List<String> getTextFromWebElements(String locator){


        List<WebElement> element = new ArrayList<WebElement>();
        List<String> text = new ArrayList<String>();
        element = driver.findElements(By.cssSelector(locator));
        for(WebElement web:element){
            text.add(web.getText());
        }

        return text;
    }



    //verifying >>>>>>><<<<<<<<<<<<>>>>>>>>>>>><<<<<<<<<<<<<>>>>>>>

    public void verifyRadioButtonSelection(String locator) {
        WebElement roundTripRadioBtn = driver.findElement(By.id(locator));

        boolean radioButton = roundTripRadioBtn.isSelected();

        System.out.println(radioButton);

        if (radioButton = true) {
            System.out.println("(Passed) Radio Button is selected");

        } else {
            System.out.println("(failed) Radio button not selected ");
        }


    }

    public void verifyTextFieldisDisplayed(String locator) {

        WebElement textField = driver.findElement(By.id(locator));
        boolean textFieldObject = textField.isDisplayed();

        if (textFieldObject = true) {
            System.out.println("(Pass) text field is present");

        } else {

            System.out.println("(Fail) Text field is not present");

        }
    }

    //verify a button is present
    public void verifyButtonIsPresent(String locator, String True, String False) {
        WebElement button = driver.findElement(By.xpath(locator));
        boolean verifyButton = button.isDisplayed();

        if (verifyButton = true) {
            System.out.println(True);

        } else {
            System.out.println(False);

        }
    }




    /*sleep*/
    public void sleepFor(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }



    //get list of elements by xpath
    public List<WebElement> getListOfWebElementsByXpath(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.xpath(locator));
        return list;

    }

    public List<WebElement> getListOfWebElementsByID(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));

        System.out.println(list.toString());

        return list;
    }

    public List<WebElement> printListOfWebElementsByID(String locator) {

        WebElement element = driver.findElement(By.id(locator));
        Select sel = new Select(element);
        List<WebElement> options = sel.getOptions();
        int size = options.size();
        System.out.println("***Data from WebApp***");

        for (int i = 0; i < size; i++) {

            String optionName = options.get(i).getText();
            System.out.println(optionName);

        }


        return options;
    }



    public List<String> getListOfString(List<WebElement> list) {
        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }
        return items;
    }



    public String getCurrentPageUrl() {
        String url = driver.getCurrentUrl();

        System.out.println(url.toString());

        return url;
    }


    //used to capture screen shot create file name
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) Math.random() * characters.length();
            sb.append(characters.charAt(index));

        }
        return sb.toString();


    }


    //handling Alert
    public boolean isAlertPresent() {

        try{
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void alertAccept() throws InterruptedException {

        WebDriver driver = null;
        Alert alert = driver.switchTo().alert();
        Thread.sleep(5000);

        alert.accept();

        Thread.sleep(5000);
    }
    //same as alertAccept method
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }


    public void getAlertText(Alert verifiedText) {

        Alert text = driver.switchTo().alert();
        System.out.println("Text of the alert is : " + text);

        if (verifiedText != text) {
            System.out.println("alert does not equal : " + verifiedText);

        }

    }


    //iFrame Handle
    public void iframeHandle(WebElement element) {

        //make sure you get the id or name of the iframe and pass it as element
        driver.switchTo().frame(element);

    }

    //counting iframe handles
    public void countIframeHandles(String tagNameLocator) {

        int iFrameElements = driver.findElements(By.tagName(tagNameLocator)).size();

        System.out.println("total count of iframes on this page is : " + iFrameElements);

    }

    public void goBackToHomeWindow(){


        driver.switchTo().defaultContent();
    }


    //Working with Window Handles
    public void getWindowHandle() {
        //returns parent window handle
        String primeWindow = driver.getWindowHandle();

    }

    //switching from parent window to child window
    public void switchParentToChildWindow() {

        Set<String> allWindows = driver.getWindowHandles();

        Iterator<String> allWindow = allWindows.iterator();

        String parentWindow = allWindow.next();

        String childWindow = allWindow.next();

        driver.switchTo().window(childWindow);



    }

    public void getAllWindowHandles() {

        Set<String> allWindows = driver.getWindowHandles();

        System.out.println(allWindows);

    }


    public void navigateBack(){


        driver.navigate().back();
    }


    public void navigateForward(){
        driver.navigate().forward();
    }

    //Synchronization
    public void waitUntilClickAble(String locator){



        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

    }


    public void implicitWait(Long waitTime) {

        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);


    }

    public void explicitWait(Long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver,waitTime);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
        //check the different methods that the ExpectedCondition class has to offer.
    }

    //use this as an example to all other wait types
    public void waitUntilVisible(By locator){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    //Explicit wait for an element to be present and then utilize it
    public WebElement waitForElement(By locator, int timeout) {
        WebElement element = null;
        try {
            //create an element object before action

            System.out.println("waiting for maximum :: " + timeout + "seconds for the element to be available");
            WebDriverWait wait = new WebDriverWait(driver, 3);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("element appeared on the webpage");

        } catch (Exception e) {

            System.out.println("element not appeared on the webpage");

        }
        return element;

    }

    public void waitUntilSelectable(By locator){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));

        //create an element object before action
    }


    public void mouseHoverByCSS(String locator){
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }


    public void mouseHoverByXpath(String locator){
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }



}
