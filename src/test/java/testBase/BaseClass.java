package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
  @BeforeClass(groups= {"Sanity","Master","Regression","DataDriven"})
  @Parameters({"os","browser"})
	    public void setup(String os, String br) throws IOException 
		{
	       
	        
	        //Loading config.properties file
	       
	        FileReader file = new FileReader("./src//test//resources/config.properties");
	        p=new Properties();
	        p.load(file);
	        logger=LogManager.getLogger(this.getClass());
	        String executionEnv = p.getProperty("execution_env");
	        //for headless mode
	        if (executionEnv.equalsIgnoreCase("remote")) {

	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--headless=new");   // headless
	            options.addArguments("--disable-gpu");
	            options.addArguments("--window-size=1920,1080");
	            options.addArguments("--no-sandbox");
	            options.addArguments("--disable-dev-shm-usage");

	            DesiredCapabilities capabilities = new DesiredCapabilities();
	            capabilities.setBrowserName("chrome");
	            capabilities.setPlatform(Platform.LINUX);

	            capabilities.merge(options);

	            driver = new RemoteWebDriver(
	                    new URL("http://localhost:4444"),
	                    capabilities
	            );
	        }

	        //for headed mode
	       /* if (executionEnv.equalsIgnoreCase("remote")) {

	            ChromeOptions options = new ChromeOptions();
	            // ❌ DO NOT add --headless
	            options.addArguments("--start-maximized");

	            DesiredCapabilities capabilities = new DesiredCapabilities();
	            capabilities.setBrowserName("chrome");
	            capabilities.setPlatform(Platform.LINUX);
	            capabilities.merge(options);

	            driver = new RemoteWebDriver(
	                    new URL("http://localhost:4444"),
	                    capabilities
	            );
	        }*/

	         
	        else if (executionEnv.equalsIgnoreCase("local"))
	        {
		        switch(br.toLowerCase())
		        {
		        case "chrome":driver = new ChromeDriver();break;
		        case "edge":driver = new EdgeDriver();break;
		        case "firefox":driver = new FirefoxDriver();break;
		        default: System.out.println("invalid browser");return;
		        }
	        }
	        
	        //for headless mode in local
	        /*
	         else if (executionEnv.equalsIgnoreCase("local"))
	          {

			    ChromeOptions options = new ChromeOptions();
			    options.addArguments("--headless=new");
			    options.addArguments("--window-size=1920,1080");
			
			    driver = new ChromeDriver(options);
			  }
           */
	        
	        driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));// reading URL from properties file
			driver.manage().window().maximize();
		}
	 


public String randomeString()
{
	String generatedstring= RandomStringUtils.randomAlphabetic(5);
	return generatedstring;
}
public String randomeNumber()
{
	String generatednumber= RandomStringUtils.randomNumeric(10);
	return generatednumber;
}
public String randomeAlphaNumeric()
{
	String generatedstring=RandomStringUtils.randomAlphabetic(3);
	String generatednumber=RandomStringUtils.randomNumeric(3);
	return (generatedstring+"@"+generatednumber);
}

public String captureScreen(String tname)
{
	String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot takesScreenshot= (TakesScreenshot)driver;
	File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);
	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
	File targetFile=new File(targetFilePath);
	sourceFile.renameTo(targetFile);
	return targetFilePath;
}
@AfterClass(groups= {"Sanity","Master","Regression","DataDriven"})
public void teardown()
{
	driver.quit();
}
}

