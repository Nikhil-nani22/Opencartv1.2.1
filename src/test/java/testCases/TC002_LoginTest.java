package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

@Listeners(utilities.ExtentReportManager.class)
public class TC002_LoginTest extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
 public void verify_login()
 {
		driver.manage().deleteAllCookies();
	    driver.navigate().refresh();
	 logger.info("**starting TC002_LoginTest **");
	 try {
		 //HomePage
		 HomePage hp=new HomePage(driver);
		 hp.clickMyAccount();
		 hp.clickLogin();
		 // ✅ LOGIN STEP WAS MISSING
		    LoginPage lp = new LoginPage(driver);
		    lp.setEmail(p.getProperty("email"));
		    lp.setPassword(p.getProperty("password"));
		    lp.clickLogin();
	  //MyAccount
	    MyAccountPage macc=new MyAccountPage(driver);
	    boolean targetPage = macc.isMyAccountpageExists();
	    //Assert.assertTrue(targetPage);
	    Assert.assertTrue(targetPage);
	    //Assert.assertEquals(targetPage, true, "Login failed");
        }
      catch(Exception e)
	 {
    	Assert.fail();  
	 }
	 logger.info("****Tc_02_LoginTestCase completed****");
 }
}
 
