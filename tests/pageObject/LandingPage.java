package pageObject;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Created by elve on 8/11/2015.
 */
public class LandingPage {

    private WebDriver driver;

    @FindBy(id="postcode")
    WebElement postCode;

    @FindBy(xpath="//span/button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@ng-repeat='council in councilsInfo']")
    WebElement councilsInfo;

    @FindBy(xpath = "//div[@ng-repeat='council in councilsInfo']/div[1]/a")
    WebElement councilAvatar;

    @FindBy(xpath = "//div[@ng-repeat='council in councilsInfo']/div[2]//i[@class='fa fa-map-marker']")
    WebElement mapIcon;
    @FindBy(xpath = "//div[@ng-repeat='council in councilsInfo']/div[2]//a[@href]")
    WebElement councilAddress;
    @FindBy(xpath = "//div[@ng-repeat='council in councilsInfo']/div[3]//img[@title='Apple Store Button']")
    WebElement appStoreIcon;
    @FindBy(xpath = "//div[@ng-repeat='council in councilsInfo']/div[3]//img[@title='Google Play Button']")
    WebElement playStoreIcon;
    @FindBy(xpath = "//header[@id='header']//div[@class='col-sm-7']//div[1]/span/button")
    WebElement closeIcon;





    public void inputPostCodeAndSearch(String postCodeValue){
        postCode.clear();
        postCode.sendKeys(postCodeValue);
        searchButton.click();

    }


    public WebElement getCouncilsInfo() {
        return councilsInfo;
    }
    public WebElement getCouncilAvatar() {
        return councilAvatar;
    }
}
