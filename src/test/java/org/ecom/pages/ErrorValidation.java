package org.ecom.pages;


import org.ecom.Test.BaseTest;
import org.ecom.pageobjects.Cartpage;
import org.ecom.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.ecom.Test.Retry;

public class ErrorValidation extends BaseTest {
	
	@Test(retryAnalyzer = Retry.class)
	public void VerifyLoginError() {
		// TODO Auto-generated method stub
	ProductCatalogue prod = landing.loginuser("kab@yopmail.com", "Test@1523");
	String errormsg= landing.errormessage();
	Assert.assertEquals(errormsg, "Incorrect email or password.");
	System.out.println(errormsg);
	}
	@Test(retryAnalyzer = Retry.class)
	public void VerigyInvalidProduct() {
		// TODO Auto-generated method stub
		String ProductName= "ZARA COAT 3";
		ProductCatalogue prod = landing.loginuser("karanb@yopmail.com", "Test@123");
		prod.waitforloginsuccess();
		prod.AddProducttocart(ProductName);
		Cartpage cartpage = prod.GotoCartpage();
		Boolean match= cartpage.VerifyProductname("ZARA COAT 35");
		Assert.assertFalse(match);
		System.out.println(match);
	}
}
