import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LandingPage;


import static org.junit.Assert.fail;

public class AddImmediateNotice {
    public static final String INPUT_FIRSTNAME = "firstName";
    public static final String FIRSTNAME = "elena";
    public static final String INPUT_LASTNAME = "lastName";
    public static final String LASTNAME = "test";
    public static final String INPUT_EMAIL_FOR_NOT_REGISTERED_COUNCIL = "email";
    public static final String INPUT_EMAIL = "inputEmail";
    public static final String EMAIL = "elena.verkholaz1@gmail.com";
    public static final String INPUT_PASSWORD = "inputPassword";
    public static final String PASSWORD = "1111111a";
    public static final String INPUT_ADDRESS = "address";
    public static final String ADDRESS = "rg249na";
    public static final String ADDRESS_UPDATED = "b11bb";
    public static final String INPUT_POSTCODE = "postcode";
    public static final String POSTCODE1 = "wa11ag";
    public static final String POSTCODE2 = "ww11ag";
    public static final String INPUT_RADIUS = "radius";
    public static final String RADIUS = "450";
    public static final String INPUT_REFID = "referenceID";
    public static final String REFID = "test24";
    public static final String REFID_UPDATED = "up-test24";
    public static final String INPUT_URN = "urn";
    public static final String URN = "urn-24";
    public static final String URN2 = "urn-25";
    public static final String INPUT_TITLE = "title";
    public static final String TITLE = "title for notice test24";
    public static final String TITLE_UPDATED = "updated title for notice test24";
    public static final String WEBSITE = "website";
    public static final String LINK = "google.co.uk";
    public static final String LINK_EXPECTED = "http://google.co.uk";
    public static final String LINK_UPDATED = "notiz.co.uk";
    public static final String CATEGORY = "General";
    public static final String CATEGORY_UPDATED = "Planning";
    public static final String CATEGORY_CHECK = "general";
    public static final String CATEGORY_UPDATED_CHECK = "planning";
    public static final String OVERRIDE_STATUS2 = "override2";
    public static final String OVERRIDE_STATUS2_VALUE = "no";
    public static final String STANDARD_DESCRIPTION = "No further information given, please contact the council for more information.";
    public static final String ERROR_INVALID_POSTCODE = "This postcode has not been recognised. Please check and try again";
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver","E:\\Program Files\\Drivers\\Chrome\\chromedriver.exe" );
//        driver = new ChromeDriver();
//        System.setProperty("webdriver.InternetExplorer.driver","E:\\Program Files\\Drivers\\IE\\IEDriverServer.exe" );
//        driver = new InternetExplorerDriver();
        baseUrl = "http://ec2-54-78-98-155.eu-west-1.compute.amazonaws.com:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }




    @Test
    public void testNoticeCouncilLogin() throws Exception {
        driver.get(baseUrl + "/");

//SEARCH COUNCIL WITH INVALID POSTCODE AND CHECK THAT ERROR WILL BE DISPLAYED
         LandingPage page = PageFactory.initElements(driver, LandingPage.class);
         page.inputPostCodeAndSearch(POSTCODE2);
//        сlearAndType(INPUT_POSTCODE, POSTCODE2);
//        clickElementByXpath("//span/button[@type='submit']");

//        page.waitForElementPresent();
        waitForElementPresentByXpath("//div[@class='alert alert-danger ng-binding']");
        assertEquals(ERROR_INVALID_POSTCODE, driver.findElement(By.xpath("//div[@class='indexAlertContainer']/div[1]")).getText());

//SEARCH REGISTERED COUNCIL
        page.inputPostCodeAndSearch(ADDRESS_UPDATED);
//        сlearAndType(INPUT_POSTCODE, ADDRESS_UPDATED);
//        clickElementByXpath("//span/button[@type='submit']");

        WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOf(page.getCouncilsInfo()));

        assertTrue(page.getCouncilAvatar().isDisplayed());


        checkIfElementPresent("//div[@ng-repeat='council in councilsInfo']/div[1]/a");
        checkIfElementPresent("//div[@ng-repeat='council in councilsInfo']/div[2]//i[@class='fa fa-map-marker']");
        checkIfElementPresent("//div[@ng-repeat='council in councilsInfo']/div[2]//a[@href]");
        checkIfElementPresent("//div[@ng-repeat='council in councilsInfo']/div[3]//img[@title='Apple Store Button']");
        checkIfElementPresent("//div[@ng-repeat='council in councilsInfo']/div[3]//img[@title='Google Play Button']");
        clickElementByXpath("//header[@id='header']//div[@class='col-sm-7']//div[1]/span/button");


//SEND MESSAGE FOR NOT REGISTERED COUNCIL
//        сlearAndType(INPUT_POSTCODE, POSTCODE1);
//        clickElementByXpath("//span/button[@type='submit']");
//        waitForElementPresentByXpath("//form[@role='form']");
//         сlearAndType(INPUT_FIRSTNAME, FIRSTNAME);
//         сlearAndType(INPUT_LASTNAME, LASTNAME);
//         сlearAndType(INPUT_EMAIL_FOR_NOT_REGISTERED_COUNCIL, EMAIL);
//         clickElementById("localAuthority");
//         driver.findElement(By.id("localAuthority")).sendKeys(Keys.TAB);
//         clickElementByXpath("//div//button[@ng-click='notifyMeAction()']");


//LOGIN AS COUNCIL
        clickElementByXpath("//div[@id='bs-example-navbar-collapse-1']//a[@class='page-scroll btn btn-wbor']");
//        TestHelper.clickElement(driver, "//div[@id='bs-example-navbar-collapse-1']//a[@class='page-scroll btn btn-wbor']").click();
        waitForElementPresent(INPUT_EMAIL);
        сlearAndType(INPUT_EMAIL, EMAIL);
        сlearAndType(INPUT_PASSWORD, PASSWORD);
        clickElementByXpath("//input[@value='Login']");


//CREATION OF IMMEDIATE NOTICE
    // click Add Notice button
        clickElementByXpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[3]/a");
        waitForElementPresentByXpath("//div[@class='row']//li/a");
    //click Immediate button
        clickElementByXpath("//div[@id='wrapper']//ul[@class='nav nav-pills nav-justified']//li[4]/a");
        waitForElementPresentByXpath("//input[@id='address']");
    //input data to the Immediate Notice form
        сlearAndType(INPUT_ADDRESS, ADDRESS);
        driver.findElement(By.id("address")).sendKeys(Keys.TAB);

        сlearAndType(INPUT_RADIUS, RADIUS);
        driver.findElement(By.id("radius")).sendKeys(Keys.TAB);

        driver.findElement(By.id(OVERRIDE_STATUS2)).click();
        сlearAndType(INPUT_REFID, REFID);
        new Select(driver.findElement(By.id("category"))).selectByVisibleText(CATEGORY);
        сlearAndType(INPUT_TITLE, TITLE);
        сlearAndType(WEBSITE, LINK);
        driver.findElement(By.id("address")).sendKeys(Keys.TAB);
    //Add Immediate Notice
        driver.findElement(By.xpath("//input[@type='submit']")).click();

//CHECK THAT ADDED NOTICE IS DISPLAYED AT THE TOP PART OF LIST
        waitForElementPresentByXpath("//table[@class='table']//tbody[1]//td[4]");
        assertEquals(REFID, driver.findElement(By.xpath("//table[@class='table']//tbody[1]//td[3]")).getText());
        assertEquals(TITLE, driver.findElement(By.xpath("//table[@class='table']//tbody[1]//td[4]")).getText());
        assertEquals(CATEGORY_CHECK, driver.findElement(By.xpath("//table[@class='table']//tbody[1]//td[5]")).getText());
        checkIfElementPresent("//table[@class='table']//tbody[1]//span[@class='glyphicon glyphicon-pencil']");
        checkIfElementPresent("//table[@class='table']//tbody[1]//span[@class='glyphicon glyphicon-remove delColor']");


//CHECK NOTICE DATA ON NOTICE DETAILS SCREEN
        clickElementByXpath("//table[@class='table']//tbody[1]//td[4]/a");

        waitForElementPresentByXpath("//div[@class='row notices-list-sidebar']");
        assertEquals(TITLE, driver.findElement(By.xpath("//div[@id='wrapper']//div[@class='col-md-7 nd-description-container']/div[1]/div")).getText());
        assertEquals(STANDARD_DESCRIPTION, driver.findElement(By.xpath("//div[@id='wrapper']//div[@class='col-md-7 nd-description-container']/div[4]/div")).getText());
        assertEquals(REFID, driver.findElement(By.xpath("//div[@class='row notices-list-sidebar']/div/div[1]/div[2]")).getText());
        assertEquals(CATEGORY_CHECK, driver.findElement(By.xpath("//div[@class='row notices-list-sidebar']/div/div[2]/div[2]")).getText());
        assertEquals(OVERRIDE_STATUS2_VALUE, driver.findElement(By.xpath("//div[@class='row notices-list-sidebar']/div/div[3]/div[2]")).getText());
        assertEquals(ADDRESS, driver.findElement(By.xpath("//div[@class='row notices-list-sidebar']/div/div[6]/div[2]")).getText());
        assertEquals(RADIUS, driver.findElement(By.xpath("//div[@class='row notices-list-sidebar']/div/div[7]/div[2]")).getText());
        assertEquals("http://"+LINK, driver.findElement(By.xpath("//div[@class='row notices-list-sidebar']/div/div[8]/div[2]")).getText());
        checkIfElementPresent("//div[@class='angular-google-map-container']");
        checkIfElementPresent("//i[@class='glyphicon glyphicon-pencil']");
        checkIfElementPresent("//i[@class='glyphicon glyphicon-trash']");


//EDIT NOTICE FROM NOTICE DETAILS SCREEN
       clickElementByXpath("//i[@class='glyphicon glyphicon-pencil']");
    //TITLE OF SCREEN SHOULD BE "EDIT NOTICE"
       assertEquals("Edit Notice", driver.findElement(By.xpath("//div[@class='text-center']/h1")).getText());
       сlearAndType(INPUT_TITLE, TITLE_UPDATED);
       сlearAndType(INPUT_REFID, REFID_UPDATED);
        new Select(driver.findElement(By.id("category"))).selectByVisibleText(CATEGORY_UPDATED);
       clickElementByXpath("//input[@type='submit']");


//CHECK THAT NOTICE HAS BEEN UPDATED AFTER EDIT
        waitForElementPresentByXpath("//table[@class='table']//tbody[1]");
        assertEquals(REFID_UPDATED, driver.findElement(By.xpath("//table[@class='table']//tbody[1]//td[3]")).getText());
        assertEquals(TITLE_UPDATED, driver.findElement(By.xpath("//table[@class='table']//tbody[1]//td[4]")).getText());
        assertEquals(CATEGORY_UPDATED_CHECK, driver.findElement(By.xpath("//table[@class='table']//tbody[1]//td[5]")).getText());
        checkIfElementPresent("//table[@class='table']//tbody[1]//span[@class='glyphicon glyphicon-pencil']");
        checkIfElementPresent("//table[@class='table']//tbody[1]//span[@class='glyphicon glyphicon-remove delColor']");


//EDIT NOTICE FROM NOTICES LIST
        clickElementByXpath("//table[@class='table']//tbody[1]//span[@class='glyphicon glyphicon-pencil']");

        сlearAndType(INPUT_ADDRESS, ADDRESS_UPDATED);
        driver.findElement(By.id("address")).sendKeys(Keys.TAB);
        сlearAndType(INPUT_RADIUS, RADIUS);
        driver.findElement(By.id("radius")).sendKeys(Keys.TAB);
        сlearAndType(INPUT_URN, URN);
        clickElementByXpath("//input[@type='submit']");


//DELETE NOTICE WHICH HAS BEEN FOUND
        waitForElementPresentByXpath("//table[@class='table']/tbody");
        clickElementByXpath("//tbody[1]//span[@class='glyphicon glyphicon-remove delColor']");
        clickElementByXpath("//table[@class='table']/tbody/tr[2]/td/a[1]");


//CHECK THAT STATUS HAS BEEN CHANGED
        checkIfElementPresent("//table[@class='table']//tbody[1]//span[@class='glyphicon glyphicon-trash']");


//ADD NEW NOTICE
        // click Add Notice button
        clickElementByXpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[3]/a");
        waitForElementPresentByXpath("//div[@class='row']//li/a");
        //click Immediate button
        clickElementByXpath("//div[@id='wrapper']//ul[@class='nav nav-pills nav-justified']//li[4]/a");
        waitForElementPresentByXpath("//input[@id='address']");
        //input data to the Immediate Notice form
        сlearAndType(INPUT_ADDRESS, ADDRESS);
        driver.findElement(By.id("address")).sendKeys(Keys.TAB);

        сlearAndType(INPUT_RADIUS, RADIUS);
        driver.findElement(By.id("radius")).sendKeys(Keys.TAB);

        driver.findElement(By.id(OVERRIDE_STATUS2)).click();
        сlearAndType(INPUT_REFID, REFID);
        new Select(driver.findElement(By.id("category"))).selectByVisibleText(CATEGORY);
        сlearAndType(INPUT_TITLE, TITLE);
        сlearAndType(WEBSITE, LINK);
        сlearAndType(INPUT_URN, URN2);
        driver.findElement(By.id("address")).sendKeys(Keys.TAB);
        //Add Immediate Notice
        driver.findElement(By.xpath("//input[@type='submit']")).click();


//SEARCH NOTICE BY URN
        waitForElementPresentByXpath("//div[@class='input-group input-group-lg']");
        clearAndTypeXpath("//div[@class='input-group input-group-lg']/input", URN2);
        clickElementByXpath("//i[@class='fa fa-search']");
        waitForElementPresentByXpath("//table[@class='table']//tbody[1]");
     //CHECK THAT ELEMENT IS DISPLAYED
//        assertEquals(REFID, driver.findElement(By.xpath("//table[@class='table']//tbody[1]/tr[1]/td[4]")).getText());

//SHOW ALL NOTICES LIST
        clickElementByXpath("//i[@class='glyphicon glyphicon-remove-sign']");
        checkIfElementPresent("//tbody[2]");

    }


    //**************************** METHODS ***********************************************************
//    private void clickElementByXpath(String findElement) {
//        TestHelper.clickElement(driver, findElement).click();
//    }

    private void clickElementByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    private void clickElementById(String id) {
        driver.findElement(By.id(id)).click();
    }

    private void waitForElementPresent(String id) throws InterruptedException {
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.id(id))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }
    private void waitForElementPresentByXpath(String ElementXpath) throws InterruptedException {
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath(ElementXpath))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }

    private void checkIfElementPresent(String ElementXpath) {
        try {
            assertTrue(isElementPresent(By.xpath(ElementXpath)));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    private void сlearAndType(String id, String value) {
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(value);
    }

    private void clearAndTypeXpath(String ElementXpath, String value) {
        driver.findElement(By.xpath(ElementXpath)).clear();
        driver.findElement(By.xpath(ElementXpath)).sendKeys(value);
    }



    @After
    public void tearDown() throws Exception {
        driver.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
