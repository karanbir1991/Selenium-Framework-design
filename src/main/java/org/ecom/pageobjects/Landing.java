package org.ecom.pageobjects;

import org.ecom.abstractcomponent.Abstractcomponent;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landing extends Abstractcomponent {
   WebDriver driver;
   public Landing(WebDriver driver){
	   super(driver);
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(id="userEmail")
   WebElement userEmail;
   
   @FindBy(id="userPassword")
   WebElement userPassword;
   
   @FindBy(id="login")
   WebElement Submit;
   
   @FindBy(xpath ="//div[contains(@class,'toast-error')]//div")
   WebElement errortoastmessage;
 
   
   
   public ProductCatalogue loginuser(String email, String Password) {
	   userEmail.sendKeys(email);
	   userPassword.sendKeys(Password);
	   visibleElementwait(Submit,10);
	   clickableElementwait(Submit, 10);
	   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Submit);

	   try {
		   Submit.click();
		} catch (ElementClickInterceptedException e) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Submit);
		}
	   ProductCatalogue prod = new ProductCatalogue(driver);
	   return prod;
   }
   public String errormessage() {
	   visibleElementwait(errortoastmessage,5);
	   return errortoastmessage.getText();
   }
  
}
