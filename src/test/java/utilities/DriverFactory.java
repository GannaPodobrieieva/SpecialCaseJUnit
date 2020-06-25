package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverFactory {	
	
	public static WebDriver open(String browserType) {		
		
		String projectPath = System.getProperty("user.dir");
		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"\\drivers\\chromedriver.exe");
//			System.out.println("Using Chrome");
			return new ChromeDriver();	
		} 
		else if(browserType.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", projectPath+"\\drivers\\operadriver.exe");
//			System.out.println("Using Opera");
			return new OperaDriver();	
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath+"\\drivers\\geckodriver.exe");
//			System.out.println("Using FF");
			return new FirefoxDriver();
		}			
	}

}
