
package pages;




import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.controlActions;


public class loginPage extends controlActions  {
	
	
	@FindBy(xpath ="//input[@id='userEmail']")
	WebElement emailElement;
	
	@FindBy(xpath ="//input[@id='userPassword']")
	WebElement passwordElement;
	
	@FindBy(xpath ="//input[@id='login']")
	WebElement submitBtnElement;
	
	@FindBy(xpath="//div[@aria-label='Login Successfully']")
	WebElement loginSuccesfullyMsg;
	
	@FindBy(xpath = "//div[text()='*Email is required']")
	WebElement emailIsRequiredMessageElement;
	
	@FindBy(xpath = "//div[text()='*Password is required']")
	WebElement passwordIsRequiredMessageElement;
	
	
	
	public loginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String email, String password) {

		 enterEmail(email);
		 
		 enterPassword(password) ;
		 
	     clickOnLoginBtn();
		
	}
	
	public void clickOnLoginBtn() {
		
		submitBtnElement.click();
		System.out.println("step- submit");
		}
	
	public void enterEmail(String email) {
		
		emailElement.sendKeys(email);
		
	}
	
	public void enterPassword(String password) {
		
		  System.out.println(" Step-Enter password ");
		     passwordElement.sendKeys(password);
		
	}
	
	public boolean isEmailRequiredMessageElementDisplayed() {
		
		return isElementDisplayed(emailIsRequiredMessageElement);
	}
	
	public boolean isPasswordRequiredMessageElementDisplayed() {
	
		return isElementDisplayed(passwordIsRequiredMessageElement);
		
	}
	
	
	
	
        public boolean isLoginSuccessfulDisplayed() {
			 waitUntilElementToBeVisible(loginSuccesfullyMsg);
           return loginSuccesfullyMsg.isDisplayed();
			
			
		}


		
		
	

}