package service;

import blocks.baseBlocks.HeaderBlock;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import pages.LoginPage;
import spring.annotations.Service;

import static utils.Wait.waitForVisibilityOfElement;

@Service
public class LoginService implements PropertyDataReader {
    @Autowired
    HeaderBlock headerBlock;
    @Autowired
    LoginPage loginPage;

    public void logInWithTestCredentials(){
        logInWithCustomCredentials(getUserCredentials("email"), getUserCredentials("password"));
    }

    public void logInWithCustomCredentials(String email, String password){
            if(!checkIfUserLogged()){
            headerBlock.getBtnSignIn().click();
            waitForVisibilityOfElement(loginPage.getEmailInput()).sendKeys(email);
            loginPage.getNextButton().click();
            waitForVisibilityOfElement(loginPage.getPasswordInput()).sendKeys(password);
            loginPage.getNextButton().click();
        }
    }

    private boolean checkIfUserLogged(){
        boolean isLogged = false;
        try {
            isLogged = headerBlock.getUserLogo().isDisplayed();
        } catch (NoSuchElementException ignored){
        }
        return isLogged;
    }
}
