package org.ecom.pageobjects;

import java.util.List;

import org.ecom.abstractcomponent.Abstractcomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductCatalogue extends Abstractcomponent {
	WebDriver driver; 
	public ProductCatalogue(WebDriver driver) {
		   super(driver);
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
		
	} 
	   
	   @FindBy(xpath ="//div[contains(@class,'toast-success')]")
	   WebElement loginsuccess;
	   
	   @FindBy(xpath ="//div[@class='card-body']")
	   List<WebElement> products;
	   
	   @FindBy(xpath ="//div[contains(@class,'ngx-spinner-overlay')]")
	   WebElement spinner;
	   
	   @FindBy(xpath ="//div[@aria-label='Product Added To Cart']")
	   WebElement AddtoCartSuccess;
	   
	   By addtocart = By.xpath(".//button[2]");

	   
	   public void waitforloginsuccess() {
		   invisibleElementwait(loginsuccess,5);
		   System.out.println("success");
	   }
	   
	   public List<WebElement> getproductlist(){
		   return products;
	   }
	   
	   public WebElement getproductbyname(String ProductName) {
		   WebElement prod=getproductlist().stream().
					filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		   return prod;
	   }
	   
	   public void AddProducttocart(String ProductName) {
		   getproductbyname(ProductName).findElement(addtocart).click();
		   visibleElementwait(spinner,5);
		   invisibleElementwait(AddtoCartSuccess,5);
		 
	   }
}
