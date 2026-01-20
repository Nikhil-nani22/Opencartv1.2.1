package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage
{
 public HomePage (WebDriver driver)
 {
	 super(driver);
 }
 //locators
 @FindBy(xpath="//a[@title='My Account']")
 WebElement lnkMyaccount;
 @FindBy(xpath="//a[normalize-space()='Register']")
 WebElement lnkRegister;
 @FindBy(linkText="Login") // Login link
 WebElement linkLogin;
 
 
 //Action methods
 public void clickMyAccount()
 {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
     wait.until(ExpectedConditions.elementToBeClickable(lnkMyaccount));
     lnkMyaccount.click();
 }
 public void clickRegister()
 {
	 lnkRegister.click();
 }
 public void clickLogin()
 {
	 linkLogin.click();
 }

}
