package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
  public AccountRegistrationPage(WebDriver driver)
  {
	  super(driver);
  }
  
 @FindBy(xpath="//input[@name='firstname']")
 WebElement txtFirstname;
 @FindBy(xpath="//input[@name='lastname']")
  WebElement txtLastname;
 @FindBy(xpath="//input[@name='email']")
 WebElement txtEmail;
 @FindBy(xpath="//input[@name='telephone']")
 WebElement txtTelephone;
 @FindBy(xpath="//input[@name='password']")
 WebElement txtPassword;
 @FindBy(xpath="//input[@name='confirm']")
 WebElement txtConfirmPassword;
 @FindBy(xpath="//input[@name='agree']")
 WebElement chkdpolicy;
 @FindBy(xpath="//input[@type='submit']")
 WebElement btncontinue;
 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
 WebElement msgConfirmation;

 //Actions
 public void setFirstName(String fname)
 {
	 txtFirstname.sendKeys(fname);
 }
 public void setLastName(String lname)
 {
	 txtLastname.sendKeys(lname);
 }
 public void setEmail(String email)
 {
	 txtEmail.sendKeys(email);
 }
 public void setTelephone(String tel)
 {
	 txtTelephone.sendKeys(tel);
 }
 public void setPassword(String pwd)
 {
	 txtPassword.sendKeys(pwd);
 }
 public void confirmPassword(String pwd)
 {
	 txtConfirmPassword.sendKeys(pwd);
 }
 public void setPrivacyPolicy()
 {
	 chkdpolicy.click();
 }
 public void clickcontinue()
 {
	 btncontinue.click();
 }
 public String getConfirmationmsg()
 {
	 try {
		 return(msgConfirmation.getText());
	 } catch(Exception e) {
		 return(e.getMessage());
	 } 				 
  }
}

