package org.ecom.pageobjects;

import java.util.List;

import org.ecom.abstractcomponent.Abstractcomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Orderspage extends Abstractcomponent {
	   WebDriver driver;
	   public Orderspage(WebDriver driver){
		   super(driver);
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
	   }
	   
	   @FindBy(xpath="//tr/td[2]")
	   List<WebElement> orderproductnames;
	   
	   public Boolean verifyorderfromlist(String ProductName) {
		   Boolean match=orderproductnames.stream().anyMatch(product->product.getText().equalsIgnoreCase(ProductName));
		   return match;
	   }
	 
}
