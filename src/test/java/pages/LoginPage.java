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
        wait.until(ExpectedConditions.visibilityOfElementLocated(email))
                 .sendKeys(cfg.email());
        driver.findElement(password)
                 .sendKeys(cfg.password());
        driver.findElement(submitButton)
                 .click();
        return new MainPage(driver);
    }
}
