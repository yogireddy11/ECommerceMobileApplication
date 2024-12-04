package BasePack;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseClass {
   public AppiumDriver driver;
   public WebDriverWait driverWait;
    public JavascriptExecutor executor;
    public static Properties properties;
    FileInputStream inputStream;
    public static final Logger logger = Logger.getLogger(BaseClass.class.getName());


    @BeforeSuite
    public void setup() throws MalformedURLException {
        logger.info("Starting setup processes!!");
        try {
            properties = new Properties();
            inputStream = new FileInputStream("C:\\Users\\Yogi\\IdeaProjects\\ECommerceMobileApplication\\fileProfiles.properties");
            properties.load(inputStream);
        }catch (FileNotFoundException notFoundException){
            notFoundException.getCause();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DesiredCapabilities capabilities = getDesiredCapabilities();
        String urlPath = "http://localhost:4723/wd/hub";
                driver = new AndroidDriver(new URL(urlPath),capabilities);
                logger.info("driver initialise successfully!!");
                driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
                executor = (JavascriptExecutor)driver;
        PageFactory.initElements(driver,this);
    }
    @AfterSuite
    public void exits(){
        if (driver!=null){
            driver.quit();
        }
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", properties.getProperty("platformName"));
        capabilities.setCapability("appium:platfromVersion", properties.getProperty("platformVersion"));
        capabilities.setCapability("appium:udid", properties.getProperty("UDID"));
        capabilities.setCapability("appium:appPackage", properties.getProperty("appPackage"));
        capabilities.setCapability("appium:appActivity", properties.getProperty("appActivity"));
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        capabilities.setCapability("appium:nativeWebScreenshot", true);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);
        return capabilities;
    }
}
