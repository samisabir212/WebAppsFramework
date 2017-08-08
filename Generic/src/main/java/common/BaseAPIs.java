package common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
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
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver");
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
        driver.manage().window().maximize();

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


    /************CLICK***********/

    //click by id
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

        for (int i = 0; i < 3; i++) {

            String optionName = options.get(i).getText();
            System.out.println(optionName);

        }


        return options;
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




    public static void alertAccept() throws InterruptedException {

        WebDriver driver = null;
        Alert alert = driver.switchTo().alert();
        Thread.sleep(5000);

        alert.accept();

        Thread.sleep(5000);
    }

    //handling Alert
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iframeHandle(WebElement element) {

        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow(){


        driver.switchTo().defaultContent();
    }

    //Synchronization
    public void waitUntilClickAble(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    //Explicit wait for an element to be present and then utilize it
    public WebElement waitForElement(By locator, int timeout) {
        WebElement element = null;
        try {

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
    }



}
