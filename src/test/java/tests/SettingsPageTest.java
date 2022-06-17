package tests;

import blocks.baseElements.Checkbox;
import blocks.baseElements.Toggle;
import blocks.navigationSubBlocks.Advanced;
import blocks.settingsPageBlocks.SettingsBlock.SettingsValues;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.adminPages.SettingsPage;

import pages.adminPages.SettingsPage.PlaybackSettingSubPage.PlaybackAV1SettingsValues;
import service.AssertService;
import service.LoginService;

import static org.hamcrest.Matchers.*;

@Test(groups = "UITests")
public class SettingsPageTest extends BaseTest {

    @Autowired
    SettingsPage settingsPage;
    @Autowired
    LoginService loginService;

    @BeforeMethod(alwaysRun = true)
    public void openSettingsPage() {
        loginService.logInWithTestCredentials();
        page.switchToNavigationBlock().getAdvanced().getSettingElement(Advanced.MainValuesEnum.SETTINGS).click();
    }

    @Test(priority = -1)
    public void allSettingsAreAvailable() {
        for (WebElement element : settingsPage.getSettingsBlock().getMenuSections()) {
            element.click();
            AssertService.softAssertThat("check settings page title",
                    ()-> settingsPage.getSettingTitle().getText(), equalToIgnoringCase(element.getText()));
        }
    }

    @Test
    public void allNotificationSettingsAreAvailable() {
        settingsPage.getSettingsBlock().getSetting(SettingsValues.NOTIFICATIONS).click();
        settingsPage.getNotificationSettingSubPage().getNotificationToggles().checkAll();
        var visibleToggles = settingsPage.getNotificationSettingSubPage().getNotificationToggles().getList();
        AssertService.softAssertThat("verify if all visible notification toggles are checked",
                () -> (int) visibleToggles.stream().filter(Toggle::isActive).count(), equalTo(visibleToggles.size()));

        settingsPage.getNotificationSettingSubPage().getNotificationToggles().uncheckAll();
        var visibleTogglesAfterUncheck = settingsPage.getNotificationSettingSubPage().getNotificationToggles().getList();
        AssertService.softAssertThat("verify if all visible notification toggles are unchecked",
                () -> (int) visibleTogglesAfterUncheck.stream().filter(Toggle::isActive).count(), equalTo(0));
    }

    @Test
    public void playbackSettingsAreAvailable() {
        settingsPage.getSettingsBlock().getSetting(SettingsValues.PLAYBACK).click();
        settingsPage.getPlaybackSettingSubPage().getPlaybackCheckboxes().checkAll();
        var visibleCheckboxes = settingsPage.getPlaybackSettingSubPage().getPlaybackCheckboxes().getList();
        AssertService.softAssertThat("verify, if all visible playback checkboxes are checked",
                () -> (int) visibleCheckboxes.stream().filter(Checkbox::isActive).count(), equalTo(visibleCheckboxes.size()));

        settingsPage.getPlaybackSettingSubPage().getPlaybackCheckboxes().uncheckAll();
        var visibleCheckboxesAfterUncheck = settingsPage.getPlaybackSettingSubPage().getPlaybackCheckboxes().getList();
        AssertService.softAssertThat("verify if all visible playback checkboxes are unchecked",
                () -> (int) visibleCheckboxesAfterUncheck.stream().filter(Checkbox::isActive).count(), equalTo(0));

        settingsPage.getPlaybackSettingSubPage().getPlaybackAV1SettingsRadioButtons().getButton(PlaybackAV1SettingsValues.PREFER_AV1_FOR_SD).setActive();
        AssertService.softAssertThat("\"prefer AV1 for SD content\" radio button is activated",
                () -> settingsPage.getPlaybackSettingSubPage().getPlaybackAV1SettingsRadioButtons().getButton(PlaybackAV1SettingsValues.PREFER_AV1_FOR_SD).isActive(),
                equalTo(true));
        settingsPage.getPlaybackSettingSubPage().getPlaybackAV1SettingsRadioButtons().getButton(PlaybackAV1SettingsValues.ALWAYS_PREFER_AV1).setActive();
        AssertService.softAssertThat("\"always prefer AV1\" radio button is activated",
                () -> settingsPage.getPlaybackSettingSubPage().getPlaybackAV1SettingsRadioButtons().getButton(PlaybackAV1SettingsValues.ALWAYS_PREFER_AV1).isActive(),
                equalTo(true));
        settingsPage.getPlaybackSettingSubPage().getPlaybackAV1SettingsRadioButtons().getButton(PlaybackAV1SettingsValues.AUTO).setActive();
        AssertService.softAssertThat("\"auto\" radio button is activated",
                () -> settingsPage.getPlaybackSettingSubPage().getPlaybackAV1SettingsRadioButtons().getButton(PlaybackAV1SettingsValues.AUTO).isActive(),
                equalTo(true));

        settingsPage.getPlaybackSettingSubPage().getPlaybackToggles().checkAll();
        var visibleToggles = settingsPage.getPlaybackSettingSubPage().getPlaybackToggles().getList();
        AssertService.softAssertThat("verify, if all visible playback toggles are checked",
                () -> (int) visibleToggles.stream().filter(Toggle::isActive).count(), equalTo(visibleToggles.size()));
        settingsPage.getPlaybackSettingSubPage().getPlaybackToggles().uncheckAll();
        var visibleTogglesAfterUncheck = settingsPage.getPlaybackSettingSubPage().getPlaybackToggles().getList();
        AssertService.softAssertThat("verify if all visible playback toggles are unchecked",
                () -> (int) visibleTogglesAfterUncheck.stream().filter(Toggle::isActive).count(), equalTo(0));

    }

    @Test(priority = 1)
    public void allPrivacySettingAreAvailable() {
        settingsPage.getSettingsBlock().getSetting(SettingsValues.PRIVACY).click();
        settingsPage.getPrivacySettingsSubPage().getPrivacySettingsToggles().checkAll();
        var visibleToggles = settingsPage.getPrivacySettingsSubPage().getPrivacySettingsToggles().getList();
        AssertService.softAssertThat("verify, if all visible privacy toggles are checked",
                () -> (int) visibleToggles.stream().filter(Toggle::isActive).count(), equalTo(visibleToggles.size()));
        settingsPage.getPrivacySettingsSubPage().getPrivacySettingsToggles().uncheckAll();
        var visibleTogglesAfterUncheck = settingsPage.getPrivacySettingsSubPage().getPrivacySettingsToggles().getList();
        AssertService.softAssertThat("verify if all visible privacy toggles are unchecked",
                () -> (int) visibleTogglesAfterUncheck.stream().filter(Toggle::isActive).count(), equalTo(0));
    }
}
