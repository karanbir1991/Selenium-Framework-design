package org.ecom.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ProductName= "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("karanb@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'toast-success')]")));
		List<WebElement> products= driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement prod=products.stream().
				filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.xpath(".//button[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ngx-spinner-overlay')]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@aria-label='Product Added To Cart']")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cartproducts= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match= cartproducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(ProductName));
		System.out.println(match);
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'action__submit ')]")).click();
		String ConfirmMsg= driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(ConfirmMsg);
		
		
		
	}

}
