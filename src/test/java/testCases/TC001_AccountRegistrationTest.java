package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
@Listeners(utilities.ExtentReportManager.class)
public class TC001_AccountRegistrationTest extends BaseClass {  
@Test(groups= {"Regression","Master"})
public void verify_account_registration()
 {
	logger.info("starting TC001_AccountRegistrationTest");
   try {	
   HomePage hp= new HomePage(driver);
   hp.clickMyAccount();
   logger.info("clicked on myaccount");
   hp.clickRegister();
   logger.info("clicked on registration");
   AccountRegistrationPage repage= new AccountRegistrationPage(driver);
   logger.info("providing customer details");
   repage.setFirstName(randomeString().toUpperCase());
   repage.setLastName(randomeString().toUpperCase());
   repage.setEmail(randomeString()+"@gmail.com");
   repage.setTelephone(randomeNumber());
   String password=randomeAlphaNumeric();
   repage.setPassword(password);
   repage.confirmPassword(password);
   repage.setPrivacyPolicy();
   repage.clickcontinue();
   logger.info("validating expected message");
   String confmsg= repage.getConfirmationmsg();
   Assert.assertEquals(confmsg,"Your Account Has Been Created!");
 }catch(Exception e)
   {
	logger.error("Test failed");
	logger.debug("Debug logs");
	Assert.fail();
   }
   logger.info("finished test case AccountRegistrationTest");
 }  
}

