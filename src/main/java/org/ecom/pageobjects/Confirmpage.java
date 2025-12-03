package org.ecom.pageobjects;

import org.ecom.abstractcomponent.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Confirmpage extends Abstractcomponent {
	WebDriver driver; 
	public Confirmpage(WebDriver driver) {
		   super(driver);
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
		
	} 
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement ConfirmMsgtext;
	
	public String confirmMessage() {
		String ConfirmMsg= ConfirmMsgtext.getText();
		System.out.println(ConfirmMsg);
		return ConfirmMsg;
	}
	
	
	
}
