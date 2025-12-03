package org.ecom.pageobjects;

import org.ecom.abstractcomponent.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Checkoutpage  extends Abstractcomponent {
	WebDriver driver; 
	public Checkoutpage(WebDriver driver) {
		   super(driver);
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
		
	} 
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryfield;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]")
	WebElement countrysearchresult;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement secondoption;
	
	@FindBy(xpath="//a[contains(@class,'action__submit ')]")
	WebElement submitformbutton;
	
	
	public void SelectCountryOption(String Country) {
		Actions a = new Actions(driver);
		a.sendKeys(countryfield, Country).build().perform();
		visibleElementwait(countrysearchresult,5);
		secondoption.click();
	}
	public Confirmpage submitform() {
		submitformbutton.click();
		return new Confirmpage(driver);
	}

}
