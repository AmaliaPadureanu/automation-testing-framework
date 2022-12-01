package pages;

import base.BasePage;
import utils.JavaScriptUtils;
import utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ItemDetailsPage extends BasePage {

    WebDriver driver;

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private String ADAUGA_IN_COS_BTN = "//a[@class='add2cart danger button full icon fa-basket']";
    private String WISHLIST_BTN = "//a[@class='4u plain align-center add2list-btn']//i[@class='fa fa-heart']";
    private String ITEM_TITLE = "section[id='book-main'] span[itemprop='name']";
    private String ITEM_AUTHOR = "a[itemprop='author']";
    private String READ_MORE_BTN = "//a[@class='read-more']";
    private String READ_LESS_BTN = "//a[@class='read-less']";
    private String RATE_STARS = ".stars>a";
    private String AUTHOR_LINK = "//a[@itemprop='author']";
    private String PUBLISHER_LINK = "//a[@itemprop='publisher']";
    private String ACCEPT_COOKIES_BTN = "//a[contains(text(),'Accept cookie-uri')]";
    private String REVIEW_BTN = "//a[normalize-space()='Am citit!']";
    private String TEXT_INPUT = "//textarea[@id='review']";
    private String RATE_STARS_POPUP = "//div[@class='box lightbox']//a[@class='fa fa-star']";
    private String POST_ANONYMOUSLY_CHECKBOX = "//input[@id='post_anon'][@class='small']";
    private String SAVE_REVIEW_BTN = "//button[@id='saveReview']";
    private String REVIEWS = "//section[@id='product-reviews']//div[@class='read-text serif']//h4[contains(.,'a dat nota: ')]";
    private String MODIFY_REVIEW_BTN = "//a[@id='modify-review']";
    private String REMOVE_REVIEW_BTN = "//a[@id='remove-review']";
    private String LISTS_DROPDOWN = "//i[@class='fa fa-down-open icon-right']";
    private String LISTS = "//a[@class='add2this-list']";

    public boolean addToCart() {
        driver.findElement(By.xpath(ADAUGA_IN_COS_BTN)).click();
        return WaitUtils.waitForUrlToContain(driver, "cart/added", 5);
    }

    public void addToWishlist() {
        driver.findElement(By.xpath(WISHLIST_BTN)).click();
    }

    public String getItemTitle() {
        return driver.findElement(By.cssSelector(ITEM_TITLE)).getText();
    }

    public String getItemAuthor() {
        return driver.findElement(By.cssSelector(ITEM_AUTHOR)).getText();
    }

    public boolean readMore() {
        driver.findElement(By.xpath(READ_MORE_BTN)).click();
        WaitUtils.wait(driver, 5);
        if (driver.findElement(By.xpath(READ_MORE_BTN)).getAttribute("style").equals("display: none;")) {
           return true;
        }
        return false;
    }

    public boolean readLess() {
        WebElement readLessBtn = driver.findElement(By.xpath(READ_LESS_BTN));
        JavaScriptUtils.click(driver, readLessBtn);
        WaitUtils.wait(driver, 5);
        if (driver.findElement(By.xpath(READ_LESS_BTN)).getAttribute("style").equals("display: none;")) {
            return true;
        }
        return false;
    }

    public void rate(int rating) {
        List<WebElement> stars = driver.findElements(By.cssSelector(RATE_STARS));
        stars.get(rating).click();
        WaitUtils.wait(driver, 5);
    }

    public void seeAllByAuthor() {
        String authorName = driver.findElement(By.xpath(AUTHOR_LINK)).getText();
        driver.findElement(By.xpath(AUTHOR_LINK)).click();
    }

    public void seeAllFromPublisher() {
        String publisherName = driver.findElement(By.xpath(PUBLISHER_LINK)).getText();
        driver.findElement(By.xpath(ACCEPT_COOKIES_BTN)).click();
        driver.findElement(By.xpath(PUBLISHER_LINK)).click();
    }

    public int getNrOfReviews() {
        return driver.findElements(By.xpath(REVIEWS)).size();
    }

    public void writeReview(Integer rating, Boolean postAnonymous, String reviewText) {
        driver.findElement(By.xpath(REVIEW_BTN)).click();
        driver.switchTo().activeElement();

        List<WebElement> stars = driver.findElements(By.xpath(RATE_STARS_POPUP));
        stars.get(rating).click();

        if (postAnonymous) {
            JavaScriptUtils.click(driver, driver.findElement(By.xpath(POST_ANONYMOUSLY_CHECKBOX)));
        }

        driver.findElement(By.xpath(TEXT_INPUT)).sendKeys(reviewText);
        driver.findElement(By.xpath(SAVE_REVIEW_BTN)).click();
    }

    public void addToList(String listName) {
        JavaScriptUtils.click(driver, driver.findElement(By.xpath(LISTS_DROPDOWN)));

        List<WebElement> lists = driver.findElements(By.xpath(LISTS));

        for (WebElement list : lists) {
            if (list.getText().equals(listName)) {
                list.click();
            }
        }

        driver.navigate().refresh();
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageURL() {
        return driver.getCurrentUrl();
    }
}