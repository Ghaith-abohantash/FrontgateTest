package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private final WebDriver driver;

    private final By emailField = By.cssSelector("input#email");
    private final By passwordField = By.cssSelector("input#password");
    private final By signInButton = By.cssSelector("button.login-button");  

    private final By errorMsg = By.xpath("//*[contains(text(),'Please enter')] | //*[contains(text(),'not correct')]");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForForm() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    public void login(String email, String password) throws InterruptedException {
        waitForForm();

        WebElement emailEl = driver.findElement(emailField);
        WebElement passEl = driver.findElement(passwordField);

        emailEl.clear();
        emailEl.sendKeys(email);

        passEl.clear();
        passEl.sendKeys(password);

        Thread.sleep(800);   

        WebElement btn = driver.findElement(signInButton);
        btn.click(); 
    }

    public String waitForAnyResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(errorMsg),
                    ExpectedConditions.stalenessOf(driver.findElement(signInButton))
            ));
        } catch (TimeoutException | NoSuchElementException e) {
        }

        return driver.getPageSource();
    }

    public boolean isSignedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            WebElement btn = driver.findElement(signInButton);

            wait.until(ExpectedConditions.stalenessOf(btn));
            return true;  
        } catch (NoSuchElementException e) {
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}