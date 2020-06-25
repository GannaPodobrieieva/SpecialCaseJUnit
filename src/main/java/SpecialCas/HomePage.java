package SpecialCas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {	
	private WebDriver driver;
	static Logger log = LogManager.getLogger(HomePage.class);
	
	public HomePage(WebDriver driver) {	
		this.driver = driver;			
	}
	
   String projectPath = System.getProperty("user.dir");	

	private By acceptButton = By.xpath("//div[@class='item-button button']");
	private By mainIcon = By.xpath("//div[@class='row-third']//span[@class='logo c-h'][contains(text(),'Special Case')]");
	private By nameField = By.xpath("//div[@id='wpcf7-f601-o1']//input[@placeholder='Full name']");
	private By phoneField  = By.xpath("//input[@placeholder='Phone']");
	private By emailField  = By.xpath("//input[@placeholder='Email']");
	private By positionField  = By.xpath("//div[@id='wpcf7-f601-o1']//input[@placeholder='Position']");
	private By buttonChooseFileField  = By.xpath("//input[@name='your-resume']");
	private By commentField  = By.xpath("//textarea[@placeholder='Your comment']");
	private By buttonSend  = By.xpath("//div[@id='wpcf7-f601-o1']//input[@class='wpcf7-form-control wpcf7-submit submit button']");
		

	/*The function, which waits for specific ExpectedCondition for the given amount of time in seconds*/
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}
	
	/*The function, which waits for given number of seconds for element with given locator to be visible on the page*/
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
		
	/*The function, which types given text into element with given locator*/
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);		
		driver.findElement(locator).sendKeys(text);
	}
	
	/*The function, which clicks the accept button*/
		public HomePage clickAcceptButton() {
			driver.findElement(acceptButton).click();
			return new HomePage(driver);
		}			
		
		
	/*The function, which finds out the existence of the main icon link on the page*/
	public HomePage monitorMainIconExistence () {				
		if (driver.findElements(mainIcon).size() > 0) 
			log.info("The main icon link is presented");
//			System.out.println("The main icon link is presented"); 
		else 
			log.info("There is no main icon link");
//			System.out.println("There is no main icon link");			
		return new HomePage(driver);		
	}		
		
	/*The function, which compares the current tab url to the expected url*/
	public HomePage monitorCurrentUrlToExpected (String name) {
		String nXpath = "//div[@class='row-third']/ul[@id='menu-primary']/li[@id='menu-item-%s']";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
		String expectedUrl = "https://specialcase.net/" + linkName.getText();
		
		if(driver.getCurrentUrl().equals(expectedUrl))
			log.info("The current tab URL " + linkName.getText() + driver.getCurrentUrl() + " equals to " + expectedUrl);
//			System.out.println("The current tab URL " + linkName.getText() + driver.getCurrentUrl() + " equals to " + expectedUrl);			
		else 
			log.info("The current tab URL " + driver.getCurrentUrl() + linkName.getText() + " doesn't equal to " + expectedUrl);
//			System.out.println("The current tab URL " + driver.getCurrentUrl() + linkName.getText() + " doesn't equal to " + expectedUrl);
		return this;
	}		
	
	/*The function, which compares the tab link name to the expected link name*/		
	public HomePage  monitorExistenceNameTabMenu (String name) {	
		String nXpath = "//div[@class='row-third']/ul[@id='menu-primary']/li[@id='menu-item-%s']";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
			
		if(linkName.getText().equals(linkName.getText()))
			log.info("The current link name " + linkName.getText() + " equals to " + linkName.getText());
//			System.out.println("The current link name " + linkName.getText() + " equals to " + linkName.getText());			
		else 
			log.info("The current link name doesn't equal to " + linkName.getText());
//			System.out.println("The current link name doesn't equal to " + linkName.getText());		
		
		if (linkName.isDisplayed()) 
			log.info("The tab menu link " + linkName.getText() + " is presented");
//			System.out.println("The tab menu link " + linkName.getText() + " is presented"); //The function, which finds out the existence of the tab menu link on the page
		else 
			log.info("There is no tab link" + linkName.getText());
//			System.out.println("There is no tab link" + linkName.getText());			
		
		return this;		
	}		
	
	/*The function, which clicks the tab menu link*/
	public HomePage clickTabMenu(String name) {
		String nXpath = "//div[@class='row-third']/ul[@id='menu-primary']/li[@id='menu-item-%s']";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
		Actions actions = new Actions(driver);	
		anticipationThread ();	
		actions.moveToElement(linkName).build().perform();
		anticipationThread ();	
		return new HomePage(driver);
	}		
	
	/*The function, which compares the link name to the current link name*/		
	public HomePage monitorExistenceNameSubTabMenu (String name) {	
		String nXpath = "//ul[@class='sub-menu']/li[@id='menu-item-%s']";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
		
		if(linkName.getText().equals(linkName.getText()))
			log.info("The current link name " + linkName.getText() + " equals to " + linkName.getText());
//			System.out.println("The current link name " + linkName.getText() + " equals to " + linkName.getText());			
		else 
			log.info("The current link name doesn't equal to " + linkName.getText());
//			System.out.println("The current link name doesn't equal to " + linkName.getText());		
		
		if (linkName.isDisplayed()) 
			log.info("The tab menu link " + linkName.getText() + " is presented");
//			System.out.println("The tab menu link " + linkName.getText() + " is presented"); //The function, which finds out the existence of the tab menu link on the page
		else 
			log.info("There is no tab link" + linkName.getText());
//			System.out.println("There is no tab link" + linkName.getText());			
		
		return this;		
	}		
	
	/*The function, which clicks the sub tab link*/
	public HomePage clickSubTabMenu(String name) {
		String nXpath = "//ul[@class='sub-menu']/li[@id='menu-item-%s']";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
		linkName.click();			
		return new HomePage(driver);
	}
	
	/*The function, which clicks the link in the footer*/
	public HomePage clickLinkMenuFooter(String name) {
		String nXpath = "//a[contains(text(),'%s')]";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
		linkName.click();			
		return new HomePage(driver);
	}
	
	/*The function, which expects and compares the link name to the current link name in the footer*/
	public HomePage monitorLinkMenuFooter(String name) {
		WebDriverWait wait = new WebDriverWait(driver, 5);	
		String nXpath = "//a[contains(text(),'%s')]";			
		WebElement linkName = driver.findElement(By.xpath(String.format(nXpath, name)));	
		wait.until(ExpectedConditions.visibilityOf(linkName));		
		
		String expectedUrl = "https://specialcase.net/" + linkName.getText();
		
		if(driver.getCurrentUrl().equals(expectedUrl))
			log.info("The current tab URL " + linkName.getText() + driver.getCurrentUrl() + " equals to " + expectedUrl);
//			System.out.println("The current tab URL " + linkName.getText() + driver.getCurrentUrl() + " equals to " + expectedUrl);			
		else 
			log.info("The current tab URL " + driver.getCurrentUrl() + linkName.getText() + " doesn't equal to " + expectedUrl);
//			System.out.println("The current tab URL " + driver.getCurrentUrl() + linkName.getText() + " doesn't equal to " + expectedUrl);
		
		return this;
	}
		
	/*The function, which types user name*/
	public HomePage typeUserName (String username) {
//		driver.findElement(nameField).sendKeys(username);
		type(username, nameField);		
		return this;
	}
	
	/*The function, which types phone in the phone field*/
	public HomePage typePhoneField (String phone) {
//		driver.findElement(phoneField).sendKeys(phone);
		type(phone, phoneField);
		return this;
	}
	
	/*The function, which types email in the email field*/
	public HomePage typeEmailField (String email) {
//		driver.findElement(emailField).sendKeys(email);
		type(email, emailField);
		return this;
	}
	
	/*The function, which types position in the position field*/
	public HomePage typePositionField (String position) {
//		driver.findElement(positionField).sendKeys(position);
		type(position, positionField);
		return this;
	}
	
	/*The function, which types comment in the comment field*/
	public HomePage typeCommentField (String comment) {
//		driver.findElement(commentField).sendKeys(comment);
		type(comment, commentField);
		return this;
	}
	
	/*The function, which pushes Upload button*/
	public void selectFile(String fileName) {
		log.info("Selecting '" + fileName + "' file from Files folder");
//		System.out.println("Selecting '" + fileName + "' file from Files folder");
		String filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileName;
		type(filePath, buttonChooseFileField);
		log.info("File selected");
//		System.out.println("File selected");
	}
	
	/*The function, which pushes Send button*/
	public void pushSendButton() {
		log.info("Clicking on upload button");
//		System.out.println("Clicking on upload button");
		driver.findElement(buttonSend).click();
	}
	
	/*The function, which gets names of uploaded files*/
	public String getUploadedFilesNames() {
		String names = driver.findElement(buttonChooseFileField).getText();
		log.info("Uploaded files: " + names);
//		System.out.println("Uploaded files: " + names);
		return names;
	}
	
	
	/*The function, which fills all the fields and sends CV */
	public HomePage sendFormCVWithInvalidCreds (String username, String phone, String email, String position, String comment) {
		this.typeUserName(username);
		this.typePhoneField(phone);
		this.typeEmailField(email);
		this.typePositionField(position);
		this.typeCommentField(comment);
		selectFile("cv.png");		
		pushSendButton();
		return new HomePage(driver);
	}
		
	/*The function, which scrolls the page down*/
	public HomePage scrolling () {	
		
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");	//Scrolling the page down
		return new HomePage(driver);
	}		
	
	/*The function, which let wait for 3 seconds*/
	public static void anticipationThread () {		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
}
