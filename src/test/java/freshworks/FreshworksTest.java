package freshworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FreshworksTest {

    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            //uncomment below to run tests locally
//            driver = new ChromeDriver();

            //comment below part to run tests locally
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "chrome");
            try {
                driver = new RemoteWebDriver(new URL("http://13.233.11.205:4444/wd/hub"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "firefox");
            try {
                driver = new RemoteWebDriver(new URL("http://13.233.11.205:4444/wd/hub"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.freshworks.com/");
    }

    @Test
    public void freshWorksTitleTest() {

        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "A fresh approach to customer engagement");

    }

    @Test
    public void getFooterLinksTest() {

        List<WebElement> footerLinksList = driver.findElements(By.cssSelector("ul.footer-nav li a"));
        footerLinksList.forEach(ele -> System.out.println(ele.getText()));
        Assert.assertEquals(footerLinksList.size(), 35);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
