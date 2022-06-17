package tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pages.adminPages.SettingsPage;
import service.AssertService;
import service.LoginService;
import utils.Wait;

import static org.hamcrest.Matchers.is;

@Test(groups = "UITests")
public class LoginPageTest extends BaseTest {

    @Autowired
    SettingsPage settingsPage;
    @Autowired
    LoginService loginService;


    @Test
    public void userCanLogin() {
        loginService.logInWithTestCredentials();
        Wait.forMillis(20000);
        AssertService
                .assertThat("check if user logo is displayed",
                        ()-> settingsPage.getHeaderBlock().getUserLogo().isDisplayed(), is(true));
    }
}
