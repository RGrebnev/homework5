package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    private By email = By.xpath("//div[@data-mode=\"login\"]//input[@name=\"email\"]");
    private By password = By.xpath("//div[@data-mode=\"login\"]//input[@name=\"password\"]");
    private By submitButton = By.xpath("//div[@data-mode=\"login\"]//button[contains(text(),\"Войти\")]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage authorize() {
        this.fillTextField(email, cfg.email()).fillTextField(password, cfg.password());
        driver.findElement(submitButton).click();
        logger.info("login form submitted");
        return new MainPage(driver);
    }

    private LoginPage fillTextField(By element, String data) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element)).clear();
        driver.findElement(element).sendKeys(data);
        return this;
    }
}
