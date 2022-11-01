package ssl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingSSlErrors {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setAcceptInsecureCerts(true);
		
		ChromeOptions coptions = new ChromeOptions();
		//coptions.merge(dc);
		coptions.setAcceptInsecureCerts(true);
		
		WebDriver driver = new ChromeDriver(coptions);
		driver.get("https://expired.badssl.com/");
		
		String text = driver.findElement(By.xpath("//h1")).getText();
		
		System.out.println(text);
		
		
		Thread.sleep(3000);
		driver.quit();
	}

}
