package org.ecom.abstractcomponent;

import java.time.Duration;

import org.ecom.pageobjects.Cartpage;
import org.ecom.pageobjects.Orderspage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstractcomponent {
	WebDriver driver;
	
	public Abstractcomponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	@FindBy(xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartbutton;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	   WebElement Orderheaderbutton ;
	   
	public Orderspage Orderpageredirect() {
		Orderheaderbutton.click();
		return new Orderspage(driver);
	   }
	public Cartpage GotoCartpage() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartbutton);
		clickableElementwait(cartbutton,5);
		cartbutton.click();
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;
	}
	public void visibleElementwait(WebElement element,int time) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	
	}
	public void clickableElementwait(WebElement element,int time) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	
	}
	
	public void invisibleElementwait(WebElement element,int time) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

}
