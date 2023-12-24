package testScripts;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.controlActions;
import pages.loginPage;

public class loginTest {
	loginPage loginPage;

	@BeforeMethod
	void Setup() {
		controlActions.launchBrowser();
		loginPage = new loginPage();
	}

	@Test
	void verifyLogin() {

		loginPage.login("nareshbaghel100@gmail.com", "Baghel@8010");
		boolean loginFlag = loginPage.isLoginSuccessfulDisplayed();
		Assert.assertTrue(loginFlag);
	}

	@Test
	void verifyErrorMsg() {

		System.out.println("click on login button");
		loginPage.clickOnLoginBtn();

		System.out.println("verify email error message");
		boolean emailFlag = loginPage.isEmailRequiredMessageElementDisplayed();
		Assert.assertTrue(emailFlag);
		System.out.println("verify password error message");
		boolean passwordFlag = loginPage.isPasswordRequiredMessageElementDisplayed();
		Assert.assertTrue(passwordFlag);
	}

	@Test
	void verifyEmailErrorMsg() {
		System.out.println(" Step-Enter valid password ");
		loginPage.enterPassword("Baghel@8010");

		System.out.println("click on login button");
		loginPage.clickOnLoginBtn();
		
		
		System.out.println("verify password error message not displayed");
		boolean passwordFlag = loginPage.isPasswordRequiredMessageElementDisplayed();
		Assert.assertFalse(passwordFlag);

		System.out.println("verify email error message");
		boolean emailFlag = loginPage.isEmailRequiredMessageElementDisplayed();
		Assert.assertTrue(emailFlag);

	}

	@Test
	void verifyPasswordErrorMsg() {
		System.out.println(" Step-Enter valid userName ");
		loginPage.enterEmail("nareshbaghel100@gmail");

		System.out.println("click on login button");
		loginPage.clickOnLoginBtn();
		
		System.out.println("verify email error message not displayed");
		boolean emailFlag = loginPage.isEmailRequiredMessageElementDisplayed();
		Assert.assertFalse(emailFlag);

		System.out.println("verify password error message");
		boolean passwordFlag = loginPage.isPasswordRequiredMessageElementDisplayed();
		Assert.assertTrue(passwordFlag);
		

	}

	@AfterMethod
	void closeBrowser(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			controlActions.takeScreenshot(result.getName());

		} else {

			controlActions.tearDown();
		}

	}

}
	
	
	

