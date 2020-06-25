package SpecialCasJUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import SpecialCas.HomePage;

public class HomePageTest {
	private WebDriver driver;
	private HomePage homepage;	
	private String baseUrl;
	static Logger log = LogManager.getLogger(HomePage.class);
	
	@Before		
	public void firstStep()  {			
		driver = utilities.DriverFactory.open("firefox");				
		HomePage.anticipationThread ();	
		driver.manage().window().maximize();
		baseUrl = "https://specialcase.net/";
		//driver.manage().window().setSize(new Dimension(700,800));		
		driver.get(baseUrl);	//Opening the link https://specialcase.net/
		homepage = new HomePage(driver);	
		HomePage.anticipationThread ();	
		homepage.clickAcceptButton();		
	}


	/*The test, which finds out the existence of the main icon link on the page*/
	@Test
	public void monitorMainIconExistenceTest() {	
		log.info("The test, which finds out the existence of the main icon link on the page");
//		System.out.println("The test, which finds out the existence of the main icon link on the page");
		homepage.monitorMainIconExistence();			
	}

	/*The test, which compares the current tab url to the expected url*/
	@Test
	public void monitorCurrentUrlToExpectedTest() {
		log.info("The test, which compares the current tab url to the expected url");
//		System.out.println("The test, which compares the current tab url to the expected url");
		homepage.monitorCurrentUrlToExpected("6"); //6 is related to the "Services" menu tab link
		homepage.monitorCurrentUrlToExpected("193"); //193 is related to the "Portfolio" menu tab link
		homepage.monitorCurrentUrlToExpected("1638"); //1638 is related to the "Platforms" menu tab link
		homepage.monitorCurrentUrlToExpected("249"); //249 is related to the "Reviews" menu tab link
		homepage.monitorCurrentUrlToExpected("1487"); //1487 is related to the "About" menu tab link			
	}	

	/*The test, which compares the tab link name to the current link name*/
	@Test
	public void monitorExistenceNameTabMenuTest() {
		log.info("The test, which compares the link URL to the current link URL");
//		System.out.println("The test, which compares the link URL to the current link URL");
		homepage.monitorExistenceNameTabMenu("6"); //6 is related to the "Services" menu tab link
		homepage.monitorExistenceNameTabMenu("193"); //193 is related to the "Portfolio" menu tab link
		homepage.monitorExistenceNameTabMenu("1638"); //1638 is related to the "Platforms" menu tab link
		homepage.monitorExistenceNameTabMenu("249"); //249 is related to the "Reviews" menu tab link
		homepage.monitorExistenceNameTabMenu("1487"); //1487 is related to the "About" menu tab link		
	}

	/*The test, which compares the sub tab link name to the current link name*/
	@Test
	public void monitorExistenceNameSubTabMenuTest() {
		log.info("The test, which compares the sub tab link name to the current link name");
//		System.out.println("The test, which compares the sub tab link name to the current link name");
		HomePage.anticipationThread ();	
		homepage.clickTabMenu("6"); //6 is related to the "Services" menu tab link
		HomePage.anticipationThread ();		
		homepage.monitorExistenceNameSubTabMenu("266"); //266 is related to the "Design & UI/UX" menu sub tab link
		HomePage.anticipationThread ();		
				
	}

	/*The test, which clicks the link in the footer*/
	@Test
	public void monitorClickLinkMenuFooterTest() {
		log.info("The test, which clicks the link in the footer");
//		System.out.println("The test, which clicks the link in the footer");			
		HomePage.anticipationThread();		
		homepage.scrolling();
		HomePage.anticipationThread();		
		homepage.clickLinkMenuFooter("Careers"); //Careers is related to the "Careers" link in the footer
		HomePage.anticipationThread();				
	}


	/*The test, which compares the link name to the current link name in the footer*/
	@Test
	public void monitorLinkMenuFooterTest() {
		log.info("The test, which compares the link name to the current link name in the footer");
//		System.out.println("The test, which compares the link name to the current link name in the footer");			
		HomePage.anticipationThread();		
		homepage.scrolling();
		HomePage.anticipationThread();		
		homepage.monitorLinkMenuFooter("Careers"); //Careers is related to the "Careers" link in the footer
		HomePage.anticipationThread();				
	}

	/*The test, which fills all the fields and sends CV*/
	@Test
	public void sendFormCVWithInvalidCredsTest() {
		log.info("The test, which fills all the fields and sends CV");
//		System.out.println("The test, which fills all the fields and sends CV");			
		HomePage.anticipationThread();		
		homepage.clickTabMenu("1487"); //1487 is related to the "About" menu tab link
		HomePage.anticipationThread ();		
		homepage.clickSubTabMenu("504"); //504 is related to the "Careers" menu sub tab link
		homepage.sendFormCVWithInvalidCreds("1","1","test1@test.test","1","1");			
	}
	
	

	/*The test, which quits the browser*/
	@After
	public void tearDown() {
		driver.quit();
		log.info("Test Completed Successfully");
//		System.out.println("Test Completed Successfully");	
	}	

}
