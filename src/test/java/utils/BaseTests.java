package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.time.Duration;

public class BaseTests {
    private static WebDriver driver;
    protected static HomePage homePage; //access to homePage

    @BeforeMethod
    public static void setUp(){
        //System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Links.HOME);
        homePage = new HomePage(driver); //access to homePage
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
