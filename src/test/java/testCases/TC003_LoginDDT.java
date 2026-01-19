package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
@Listeners(utilities.ExtentReportManager.class)
public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) {

	    exp = exp.trim();

	    try {
	        HomePage hp = new HomePage(driver);
	        hp.clickMyAccount();
	        hp.clickLogin();

	        LoginPage lp = new LoginPage(driver);
	        lp.setEmail(email);
	        lp.setPassword(pwd);
	        lp.clickLogin();

	        MyAccountPage macc = new MyAccountPage(driver);
	        boolean targetPage = macc.isMyAccountpageExists();

	        if (exp.equalsIgnoreCase("valid")) {

	            Assert.assertTrue(targetPage, "Valid login failed");
	            macc.clickLogout();

	        } else if (exp.equalsIgnoreCase("invalid")) {

	            Assert.assertFalse(targetPage, "Invalid login passed");

	        } else {
	            Assert.fail("Invalid test data in Excel: " + exp);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail(e.getMessage());
	    }
	    logger.info("***** Finished TC003_LoginDDT *****");
	}

 }

