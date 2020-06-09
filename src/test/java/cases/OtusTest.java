package cases;

import org.junit.jupiter.api.Test;
import pages.MainPage;

public class OtusTest extends BaseTest {

    @Test
    public void changeInfoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickLoginButton()
                .authorize()
                .openPersonalSettings()
                .addAllInfo();

        driver.manage().deleteAllCookies();

        mainPage.open()
                .clickLoginButton()
                .authorize()
                .openPersonalSettings()
                .checkPersonalInfo();
    }
}
