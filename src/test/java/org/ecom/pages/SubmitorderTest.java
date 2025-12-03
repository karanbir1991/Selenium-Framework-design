package org.ecom.pages;


import java.util.HashMap;
import java.util.List;

import org.ecom.Test.BaseTest;
import org.ecom.Test.Retry;
import org.ecom.pageobjects.Cartpage;
import org.ecom.pageobjects.Checkoutpage;
import org.ecom.pageobjects.Confirmpage;
import org.ecom.pageobjects.Orderspage;
import org.ecom.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitorderTest extends BaseTest {
	String ProductName= "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData",groups = {"Smoke"},retryAnalyzer = Retry.class)
	public void submitorder(HashMap<String, String> input) {
		// TODO Auto-generated method stub
	
	ProductCatalogue prod = landing.loginuser(input.get("email"), input.get("password"));
	prod.waitforloginsuccess();
	prod.AddProducttocart(input.get("Product"));
	Cartpage cartpage = prod.GotoCartpage();
	Boolean match= cartpage.VerifyProductname(input.get("Product"));
	Assert.assertTrue(match);
	Checkoutpage checkout = cartpage.Checkout();
	checkout.SelectCountryOption("india");
	Confirmpage confirmpage = checkout.submitform();
	String ConfirmMsg= confirmpage.confirmMessage();
	Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"submitorder"},retryAnalyzer = Retry.class)
	public void OrderHistory() {
		ProductCatalogue prod = landing.loginuser("karanb@yopmail.com", "Test@123");
		Orderspage order = prod.Orderpageredirect();
		Assert.assertTrue(order.verifyorderfromlist(ProductName));
	}
	
	
	@DataProvider
	public Object [] [] getData() throws Exception{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/org/ecom/data/DataFile.json");
		return new Object [] [] {{data.get(0)},{data.get(1)},{data.get(2)}} ;
	}
}


