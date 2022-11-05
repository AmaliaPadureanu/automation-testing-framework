package base;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected String baseURL;
    protected ContactPage contactPage;
    protected LoginPage loginPage;
    protected NavigationPage navigationPage;
    protected SearchPage searchPage;
    protected ShoppingCartPage shoppingCartPage;
    protected SignInPage signInPage;
    protected WishlistPage wishlistPage;
    protected ItemDetailsPage itemDetailsPage;
    protected UserDetailsPage userDetailsPage;

    public String email = "automationtesting630@gmail.com";
    public String password = "QAtest123";

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = WebDriverFactory.getInstance().getDriver("edge");
        baseURL = "https://www.books-express.ro/";
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }


}
