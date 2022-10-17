package parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.DataUtils;

public class patientLogin extends browser.launchBrowser {
	
	@BeforeTest
	public void launchBrowser() {
		driver = browseropen();
	}
		
	@Test(dataProvider = "getPatientData",dataProviderClass = DataUtils.class)
	public void login(String data[]) {
		
//		System.err.println(data[0]);
//		System.err.println(data[1]);
		driver.findElement(By.xpath("//input[@id='edit-name--2']")).sendKeys(data[0]);
		driver.findElement(By.xpath("//input[@id='edit-pass--2']")).sendKeys(data[1]);
		driver.findElement(By.xpath("//button[@id='edit-submit--2']")).click();
		
	}
	
	@Test(dependsOnMethods = "login")
	public void onDemand() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,60);
		WebElement allowBtn = driver.findElement(By.xpath("//button[text()='Allow']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		Boolean allowBtnIsVisible = allowBtn.isDisplayed();
		Thread.sleep(3000);
		if(allowBtnIsVisible==true) {
			wait.until(ExpectedConditions.elementToBeClickable(allowBtn));
			allowBtn.click();
		}else {
			
		}
		
		driver.findElement(By.xpath("//a[@href='/en/patient/ondemand']")).click();
		Thread.sleep(7000);
		WebElement closePop = driver.findElement(By.xpath("//div[@class='modal-buttons']/button[contains(@class,'allow')]"));
		wait.until(ExpectedConditions.visibilityOf(closePop));
		Thread.sleep(7000);
		Actions ac = new Actions(driver);
		ac.moveToElement(closePop).build().perform();
		closePop.click();
		
//		Thread.sleep(3000);
//		try {
//			wait.until(ExpectedConditions.visibilityOf(allowBtn));
//			if(allowBtnIsVisible==true) {
//				wait.until(ExpectedConditions.elementToBeClickable(allowBtn));
//				allowBtn.click();
//			}else {
//				
//			}
//		}catch(StaleElementReferenceException e) {
//			e.printStackTrace();
//		}
		Thread.sleep(7000);
		WebElement allRadioBtn = driver.findElement(By.xpath("//input[@value='All']"));
		wait.until(ExpectedConditions.elementToBeClickable(allRadioBtn));
		js.executeScript("arguments[0].click()",allRadioBtn);
		//allowBtn.click();
		Thread.sleep(3000);
		
		
		
		try {
		WebElement selectLang = driver.findElement(By.xpath("//select[@name='language']"));
		Select language = new Select(selectLang);
		language.selectByValue("English");
		
		Select specialty = new Select(driver.findElement(By.xpath("//select[@id='edit-specialty']")));
		specialty.selectByValue("26");
		Thread.sleep(3000);
		}catch(Exception e) {
			
		}
		
		
		
		WebElement onDemandReq = driver.findElement(By.xpath("//button[@value='Request for On-Demand']"));
		js.executeScript("arguments[0].click()",onDemandReq);
		driver.findElement(By.xpath("//div[@class='btn pay']")).click();
		WebElement cardHolderName = driver.findElement(By.xpath("//input[@id='edit-name']"));
		wait.until(ExpectedConditions.visibilityOf(cardHolderName));
		cardHolderName.sendKeys("Rafael Gomez");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='edit-email']")).sendKeys("say.hello.to.anna.jo+testpatient@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='edit-number']")).sendKeys("4242424242424242");
		
		Select expYear = new Select(driver.findElement(By.xpath("//select[@id='edit-exp-year']")));
		expYear.selectByValue("2024");
		
		Select expMonth = new Select(driver.findElement(By.xpath("//select[@id='edit-exp-month']")));
		expMonth.selectByValue("3");
		
		driver.findElement(By.xpath("//input[@id='edit-cvc']")).sendKeys("423");
		
		driver.findElement(By.xpath("//button[@value='Validate']")).click();
		Thread.sleep(3000);
		WebElement errorMsg = driver.findElement(By.xpath("//div[@role='alert']/h2[text()='Error message']"));
		boolean displayedErr = errorMsg.isDisplayed();
		if(displayedErr==true) {
			driver.findElement(By.xpath("//ul[@class='menu menu--patient-navigation nav']//a[text()='Dashboard']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(allowBtn));
			allowBtn.click();
			driver.findElement(By.xpath("(//div[@class='patient-details patient-dashboard'])[1]")).click();
		}else {
		WebElement continueBtn = driver.findElement(By.xpath("//a[text()='Continue']"));
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		js.executeScript("arguments[0].click()",continueBtn);
		
		if(allowBtnIsVisible==true) {
			wait.until(ExpectedConditions.elementToBeClickable(allowBtn));
			allowBtn.click();
		}else {
			
		}
		
		driver.findElement(By.xpath("//input[@id='edit-existing-symptoms-60']")).click();
		
		driver.findElement(By.xpath("//button[@value='Save']")).click();
		Thread.sleep(3000);
		WebElement onProgress = driver.findElement(By.xpath("//div[@class='oprogress__title']"));
		wait.until(ExpectedConditions.visibilityOf(onProgress));
		}
		driver.close();
	}
}
