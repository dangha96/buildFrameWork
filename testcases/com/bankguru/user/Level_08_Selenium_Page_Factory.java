package com.bankguru.user;

import common.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.LoginPageObject;
import pageFactory.MainPageObject;
import pageFactory.RegisterPageObject;

import java.util.Random;

public class Level_08_Selenium_Page_Factory extends AbstractTest {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String loginPageUrl,invalidUser,invalidPass, userID, password;
    String customerName, dobirth, address, city, phone, pin, email;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    MainPageObject mainPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {

        System.setProperty("webdriver.chrome.driver", projectPath +
                "\\browserDriver\\chromedriver.exe");
        invalidUser="ha1";
        invalidPass="123";
        customerName = "ha";
        dobirth = "01-01-1996";
        address = "ABC";
        city = "united";
        phone = "0343020504";
        pin = "134678";
        email = "ha" + randomNumber() + "@gmail.com";

        driver= getBrowserDriver(browserName);
        loginPage = new LoginPageObject(driver);

    }

    @Test
    public void verifyTC_01_Register_To_System() {
        loginPageUrl = loginPage.getLoginPageUrl();
        loginPage.clickToHereLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.inputToEmailTextBox("dangha" + randomNumber() + "@gmail.com");
        registerPage.clickToSubmitBtn();
        userID = registerPage.getUserText();
        password = registerPage.getPassText();

//        clickToElement(driver,"//a[text()='here']");
//        sendKeyToElement(driver,"//input[@name='emailid']","dangha@gmailcom");
//        clickToElement(driver,"//input[@name='btnLogin']");
//        userID=getElementText(driver,"//td[text()='User ID :']//following-sibling::td");
//        password=getElementText(driver,"//td[text()='Password :']//following-sibling::td");

    }

    @Test
    public void verify_Login() {
        registerPage.openLoginPage(loginPageUrl);
        loginPage = new LoginPageObject(driver);
        loginPage.inputInvalidUser(invalidUser);
        loginPage.inputInvalidPass(invalidPass);
        loginPage.clickToLoginBtnInvalid();
        loginPage.inputToUserTextBox(userID);
        loginPage.inputToPassTextBox(password);
        loginPage.clickToLoginBtn();
        mainPage = new MainPageObject(driver);
        mainPage.getWelcomeText();
        Assert.assertEquals(mainPage.getWelcomeText(), "Welcome To Manager's Page of Guru99 Bank");
    }
    //loginPageUrl=getCurrentUrl(driver);
//        sendKeyToElement(driver,"//input[@name='uid']",userID);
//        sendKeyToElement(driver,"//input[@name='password']",password);
//        clickToElement(driver,"//input[@name='btnLogin']");

//    @Test
//    public void open_New_Customer_Page() {
//        mainPage.openNewCustomerPage();
//        newPage = new NewCustomerPageObject(driver);
//        newPage.inputToNameTextBox(customerName);
//        newPage.inputToDobBox(dobirth);
//        newPage.inputToAddressTextBox(address);
//        newPage.inputToCityTextBox(city);
//        newPage.inputToPinTextBox(pin);
//        newPage.inputToMobileTextBox(phone);
//        newPage.inputToEmailTextBox(email);
//        newPage.inputToPasswordTextBox(password);
//        newPage.clickSubmitBtn();
//        Assert.assertEquals(newPage.getSuccessMess(), "Customer Registered Successfully!!!");
//
//    }

//    @Test
//    public void logout() {
//        newPage.clickToLogoutLink();
//
//        loginPage = new LoginPageObject(driver);
//        Assert.assertTrue(loginPage.isLoginFormDisplayed());
//    }

    public int randomNumber() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    @AfterClass
    public void afterClass() {
        removeBrowserDriver();

    }

}
