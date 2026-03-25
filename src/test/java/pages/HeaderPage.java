package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeaderPage {
    private final WebDriver driver;

    private final By myAccountButton = By.xpath(
            "//div[normalize-space()='My Account']/ancestor::*[self::button or self::a][1]"
    );

    private final By acceptCookies = By.cssSelector("button#onetrust-accept-btn-handler");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookiesIfExists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
        } catch (Exception ignored) {
        }
    }

    public void clickMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(myAccountButton)).click();
    }
}