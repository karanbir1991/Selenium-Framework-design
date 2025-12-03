package org.ecom.pageobjects;

import java.util.List;

import org.ecom.abstractcomponent.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cartpage extends Abstractcomponent {
	WebDriver driver; 
	public Cartpage(WebDriver driver) {
		   super(driver);
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
		
	} 
	@FindBy(xpath ="//li[@class='totalRow']/button")
	WebElement totalRow;
	@FindBy(xpath ="//div[@class='cartSection']/h3")
	List<WebElement> cartproducts;
	
	public Boolean VerifyProductname(String ProductName) {
		Boolean match= cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(ProductName));
		System.out.println(match);
		return match;
		
		
	}
	
	public Checkoutpage Checkout() {
		totalRow.click();
		return new Checkoutpage(driver);
	}
	//List<WebElement> cartproducts= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	//Boolean match= cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(ProductName));
	//System.out.println(match);
	//Assert.assertTrue(match);
	//driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

}
