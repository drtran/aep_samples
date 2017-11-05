package com.drkiettran.aep.selenium;

import com.drkiettran.aep.selenium.pages.*;
import junit.framework.TestCase;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

// Scenario: Adopting one pet 
//   Given I am at Puppy Adoption Agency website “http://puppies.herokuapp.com/” 
//   When I adopt and pay for a pet 
//   And I click on ‘View Details’ for a pet name ‘Brook’ 
//   And I click on ‘Adopt Me!’ button 
//   And I click on ‘Complete the Adoption’ button 
//   And I fill out the payment detail name, address, email, & payment method 
//   And I click on ‘Place Order’ button 
//   Then I should see a message “Thank you for adopting a puppy!” 

// To run this under JUnit test, make sure to include:
// -DwebDriver=CHROME or FIREFOX
// 
// You can also run this test specifically at the command prompt as follows:
//

// mvn -Dtest=AdoptingOnePetTest -DwebDriver=CHROME -Dwebdriver.chrome.driver=C:\drkiettran\bin\misc\chromedriver.exe clean test
// mvn -Dtest=AdoptingOnePetTest -DwebDriver=FIREFOX clean test

/**
 * @author ktran
 */

public class AdoptingOnePetTest {

    private WebDriver webDriver;
    private Browser browser;

    private PuppyMainPage mainPage = null;
    private DetailsPage detailsPage = null;
    private AdoptingPetsPage adoptingPetsPage = null;
    private PaymentPage paymentPage = null;

    @Test
    public void adoptingOnePetTest() {
        given_I_am_at_Puppy_Adoption_Agency_website("http://puppies.herokuapp.com");
        when_I_adopt_and_pay_for_a_pet("Brook");
        then_I_should_see_a_thank_you_message("Thank you for adopting a puppy!");
    }

    private void given_I_am_at_Puppy_Adoption_Agency_website(String url) {
        mainPage.visit(url);
    }

    private void when_I_adopt_and_pay_for_a_pet(String petName) {
        I_click_on_view_details_for_a_pet(petName);
        I_click_on_adopt_me_button();
        I_click_on_complete_the_adoption_button();
        I_fill_out_the_payment_detail("John Doe", "123 Main Street, New York, NY 10001", "jdoe@mail.com", "Check");
        I_click_on_place_order_button();
    }

    private void then_I_should_see_a_thank_you_message(String expectedNotice) {
        String actualNotice = mainPage.getNotice();
        assertEquals(expectedNotice, actualNotice);
    }

    private void I_click_on_view_details_for_a_pet(String petName) {
        mainPage.viewDetails(petName);
    }

    private void I_click_on_adopt_me_button() {
        detailsPage.adoptMe();
    }

    private void I_click_on_complete_the_adoption_button() {
        adoptingPetsPage.completeAdoption();
    }

    private void I_fill_out_the_payment_detail(String name, String address, String email, String payType) {
        paymentPage.fillOutInfo(name, address, email, payType);
    }

    private void I_click_on_place_order_button() {
        paymentPage.placeOrder();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        browser = Browser.getInstance(webDriver);
        mainPage = new PuppyMainPage();
        detailsPage = new DetailsPage();
        adoptingPetsPage = new AdoptingPetsPage();
        paymentPage = new PaymentPage();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @AfterClass
    public static void tearDownClass() {
    }
}
