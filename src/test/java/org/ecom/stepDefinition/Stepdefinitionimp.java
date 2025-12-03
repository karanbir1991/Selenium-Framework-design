package org.ecom.stepDefinition;

import java.io.IOException;

import org.ecom.Test.BaseTest;
import org.ecom.pageobjects.Cartpage;
import org.ecom.pageobjects.Checkoutpage;
import org.ecom.pageobjects.Confirmpage;
import org.ecom.pageobjects.Landing;
import org.ecom.pageobjects.ProductCatalogue;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinitionimp extends BaseTest {
	public Landing landing;
	public ProductCatalogue prod;
	public Cartpage cartpage;
	public Checkoutpage checkout;
	public Confirmpage confirmpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landing = launchbrowser();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		prod = landing.loginuser(username, password);
		prod.waitforloginsuccess();
	}
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String Product){
		
		prod.AddProducttocart(Product);
		
	}
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String Product){
		cartpage = prod.GotoCartpage();
		Boolean match= cartpage.VerifyProductname(Product);
		Assert.assertTrue(match);
		checkout = cartpage.Checkout();
		checkout.SelectCountryOption("india");
		confirmpage = checkout.submitform();
	}
	@Then("{string} message is displayed on the Confirmpage")
	public void message_is_displayed_on_the_Confirmpage(String string) throws Exception {
		String ConfirmMsg= confirmpage.confirmMessage();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase(string));
		closedriver();
	}
	@When("^Login with username (.+) and password (.+)$")
	public void Login_with_username_and_password(String name, String pass) {
		prod = landing.loginuser(name, pass);
		
	}
    
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) throws Exception {
		String errormsg= landing.errormessage();
		Assert.assertEquals(errormsg, string);
		System.out.println(errormsg);
		closedriver();
	}
	
}
