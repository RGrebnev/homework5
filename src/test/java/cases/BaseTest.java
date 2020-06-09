package cases;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        String browserType = System.getProperty("browser");
        if (browserType == null) browserType = "chrome"; //default browser (without argument)
        driver = WebDriverFactory.createNewDriver(browserType);

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
