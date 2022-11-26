package TestClasses;

import Pages.LoginPage;
import Pages.SignInPage;
import Utils.WaitUtils;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTests extends BaseTest {

    String afterSignInURL = "https://www.books-express.ro/user/details";

    @DataProvider
    public Object[][] signInDataProvider() {
        return new Object[][] {
                {"mithtest@gmail.com", "Nicole", "Smith", "testpass123"},
                {"onestest@gmail.com", "Marry", "Jones", "passtest321"},
                {"estest@gmail.com", "Joe", "Smith", "qatestpass99"},
                {"ttjtest@gmail.com", "Matt", "Jones", "testqaqa60"}
        };
    }

    @Test (dataProvider = "signInDataProvider")
    public void testSignIn(String email, String firstname, String lastname, String password) {
        signInPage = new SignInPage(driver);
        signInPage.open();
        signInPage.signInWith(email, firstname, lastname, password);
        WaitUtils.waitForUrlToContain(driver, "/user/details", 5);
        Assert.assertTrue(driver.getCurrentUrl().equals(afterSignInURL));
    }

    @AfterMethod
    public void cleanup() {
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }
}
