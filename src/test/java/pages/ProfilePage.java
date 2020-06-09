package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class ProfilePage extends AbstractPage {
    protected static final String URL = MainPage.URL + "lk/biography/personal/";
    private By saveButton = By.cssSelector("button[title=\"Сохранить и продолжить\"]");
    private By name = By.cssSelector("input[name=\"fname\"]");
    private By nameLatin = By.cssSelector("input[name=\"fname_latin\"]");
    private By lastname = By.cssSelector("input[name=\"lname\"]");
    private By lastnameLatin = By.cssSelector("input[name=\"lname_latin\"]");
    private By nameBlog = By.cssSelector("input[name=\"blog_name\"]");
    private By birthday = By.cssSelector("input[name=\"date_of_birth\"]");
    private By country = By.cssSelector("input[name=\"country\"] + div");
    private By city = By.cssSelector("input[name=\"city\"] + div");
    private By english = By.cssSelector("input[name=\"english_level\"] + div");
    private By addContactButton = By.cssSelector("button.js-formset-add");
    private By selectOptions = By.cssSelector(".lk-cv-block__select-options:not(.hide)");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void addAllInfo() {
        assertEquals(driver.getCurrentUrl(), URL, "must be lk/biography/personal/ page");
        logger.info("profile page is open");
        this.addPersonalData()
                .addMainData()
                .addFirstContact()
                .addSecondContact()
                .clickSave();
    }

    public void checkPersonalInfo() {
        assertAll("wrong personal info",
                () -> assertEquals(cfg.name(), driver.findElement(name).getAttribute("value")),
                () -> assertEquals(cfg.lastname(), driver.findElement(lastname).getAttribute("value")),
                () -> assertEquals(cfg.nameLatin(), driver.findElement(nameLatin).getAttribute("value")),
                () -> assertEquals(cfg.lastnameLatin(), driver.findElement(lastnameLatin).getAttribute("value")),
                () -> assertEquals(cfg.nameBlog(), driver.findElement(nameBlog).getAttribute("value")),
                () -> assertEquals(cfg.birthday(), driver.findElement(birthday).getAttribute("value")),
                () -> assertEquals(cfg.country(), driver.findElement(country).getText()),
                () -> assertEquals(cfg.city(), driver.findElement(city).getText()),
                () -> assertEquals(cfg.english(), driver.findElement(english).getText()),
                () -> assertEquals(cfg.contactType1(), driver.findElement(contactType(0)).getText()),
                () -> assertEquals(cfg.contactData1(), driver.findElement(contactData(0)).getAttribute("value")),
                () -> assertEquals(cfg.contactType2(), driver.findElement(contactType(1)).getText()),
                () -> assertEquals(cfg.contactData2(), driver.findElement(contactData(1)).getAttribute("value"))
                );
        logger.info("all data is OK!!!");
    }

    //fill blocks
    private ProfilePage addPersonalData(){
        return this.fillTextField(name, cfg.name())
                .fillTextField(lastname, cfg.lastname())
                .fillTextField(nameLatin, cfg.nameLatin())
                .fillTextField(lastnameLatin, cfg.lastnameLatin())
                .fillTextField(birthday, cfg.birthday())
                .fillTextField(nameBlog, cfg.nameBlog());
    }
    private ProfilePage addMainData() {
        return this.fillSelectField(country, cfg.country())
                .fillSelectField(city, cfg.city())
                .fillSelectField(english, cfg.english());
    }
    private ProfilePage addFirstContact() {
        return this.fillContact(0, cfg.contactType1(), cfg.contactData1());
    }
    private ProfilePage addSecondContact() {
        return this.clickAddContact()
                .fillContact(1, cfg.contactType2(), cfg.contactData2());
    }

    //fill single fields
    private ProfilePage fillTextField(By field, String data) {
        driver.findElement(field).clear();
        driver.findElement(field).sendKeys(data);
        assertEquals(data, driver.findElement(field).getAttribute("value"));
        logger.info("{} written in field found {}", data, field.toString());
        return this;
    }
    private ProfilePage fillSelectField(By field, String data) {
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectOptions));
        wait.until(ExpectedConditions.elementToBeClickable(driver
                .findElement(selectOptions)
                .findElement(selectOptionsValue(data))))
                .click();
        assertEquals(data, driver.findElement(field).getText());
        logger.info("{} is selected in field found {}", data, field);
        return this;
    }
    private ProfilePage fillContact(int number, String type, String data) {
        this.fillSelectField(contactType(number), type)
                .fillTextField(contactData(number), data);
        assertAll("error adding contact",
                () -> assertEquals(type, driver.findElement(contactType(number)).getText()),
                () -> assertEquals(data, driver.findElement(contactData(number)).getAttribute("value"))
                );
        logger.info("contact added. type: {}, data: {}", type, data);
        return this;
    }
    //buttons
    private ProfilePage clickAddContact() {
        driver.findElement(addContactButton).click();
        logger.info("'add contact' button clicked");
        return this;
    }
    private ProfilePage clickSave() {
        driver.findElement(saveButton).click();
        logger.info("'save' button clicked");
        return this;
    }

    //help
    private By selectOptionsValue(String data) {
        return By.cssSelector("button[title=\"" + data + "\"]");
    }
    private By contactType(int number) {
        return By.cssSelector("input[name=\"contact-" + number + "-service\"] + div");
    }
    private By contactData(int number) {
        return By.cssSelector("input[name=\"contact-" + number + "-value\"]");
    }
}
