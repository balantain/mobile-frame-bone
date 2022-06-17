package tests;


import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

@Test(groups = "UItest")
public class SingleTest extends BaseTest{
    @Autowired
    MainPage page;

    @Test
    public void checkSearchDisplayed(){
        Assert.assertTrue(page.getHeaderBlock().getSearchBlock().getSearchField().isDisplayed());
    }
}