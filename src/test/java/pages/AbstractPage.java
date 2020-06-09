package pages;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestConfig;


public abstract class AbstractPage {
    protected static TestConfig cfg = ConfigFactory.create(TestConfig.class);
    protected static final Logger logger = LogManager.getLogger("JUnit tests");
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10L);
    }
}
