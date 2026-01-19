package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
 public MyAccountPage (WebDriver driver)
 {
  super(driver);
 }
 @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
 WebElement msgHeading;
 @FindBy(xpath="//a[@class='list-group-item' and text()='Logout']")
 WebElement linkLogout;
 public boolean isMyAccountpageExists()
 {
	 try {
		 return(msgHeading.isDisplayed());
	     } catch(Exception e)
		   {
			return false; 
		   }
 }
 public void clickLogout()
 {
	 linkLogout.click();
 }
}
