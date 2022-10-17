package browser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class launchBrowser {
	
	public WebDriver driver;


	 public  WebDriver browseropen(){

		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://stage.youmedico.com/en/login");
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 return driver;
	 }

}
