package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends AbstractPage {
    protected static final String URL = cfg.testPage();
    private By loginButton = By.xpath("//button[@data-modal-id=\"new-log-reg\"]");
    private By userName = By.cssSelector(".header2-menu__item-text__username");
    private By profileLink = By.cssSelector(".header2-menu__dropdown-link[href=\"/lk/biography/personal/\"]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(URL);
        logger.info("main page opened");
        return this;
    }

    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        logger.info("clicked to login button");
        return new LoginPage(driver);
    }

    public ProfilePage openPersonalSettings() {
        assertTrue(this::checkAuth, "not authorized");
        driver.findElement(userName).click();
        wait.until(ExpectedConditions.elementToBeClickable(profileLink)).click();
        logger.info("'my profile' option is selected");
        return new ProfilePage(driver);
    }

    public Boolean checkAuth() {
        try {
            return driver.findElement(userName).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("must be authorized!");
            return false;
        }
    }

}
