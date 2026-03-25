package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.SignInPage;
import utils.ExcelUtils;
import utils.TestDataWriter;

public class LoginExcelTest extends BaseTest {

    private static final String URL = "https://www.frontgate.com/ShoppingCartView";

    @BeforeClass
    public void prepareExcel() throws Exception {
        TestDataWriter.createExcelIfNotExists();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        return ExcelUtils.readSheet(TestDataWriter.FILE_PATH, "LoginCases");
    }

    @Test(dataProvider = "loginData")
    public void loginWithExcel(String email, String password) throws Exception {
        driver.get(URL);

        HeaderPage header = new HeaderPage(driver);
        header.closeCookiesIfExists();
        header.clickMyAccount();  

        SignInPage signIn = new SignInPage(driver);
        signIn.login(email, password);

        String body = signIn.waitForAnyResult();

        if (!email.contains("@") || password.isEmpty()) {
            Assert.assertTrue(body.contains("Please enter Current Password."),
                    "Expected 'Please enter Current Password.' message");
            return;
        }

        if (email.equals(TestDataWriter.VALID_EMAIL) && !password.equals(TestDataWriter.VALID_PASSWORD)) {
            Assert.assertTrue(body.contains("Email/Password you entered is not correct"),
                    "Expected 'Email/Password you entered is not correct' message");
            return;
        }

        if (email.equals(TestDataWriter.VALID_EMAIL) && password.equals(TestDataWriter.VALID_PASSWORD)) {
            Assert.assertTrue(signIn.isSignedIn(), "Login success not detected (Sign In button still visible).");
        }
    }
}