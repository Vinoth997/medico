package parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.DataUtils;

public class doctorLogin extends browser.launchBrowser {
	
	@BeforeTest
	public void launchBrowser() {
		driver = browseropen();
	}
		
	@Test(dataProvider = "getDoctorData",dataProviderClass = DataUtils.class)
	public void login(String data[]) {
		
		driver.findElement(By.xpath("//input[@id='edit-name--2']")).sendKeys(data[0]);
		driver.findElement(By.xpath("//input[@id='edit-pass--2']")).sendKeys(data[1]);
		driver.findElement(By.xpath("//button[@id='edit-submit--2']")).click();
	}
	
	@Test(dependsOnMethods = "login")
	public void availableOnDemand() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,600);
		WebElement allowBtn = driver.findElement(By.xpath("//button[text()='Allow']"));
		
		Boolean allowBtnIsVisible = allowBtn.isDisplayed();
		Thread.sleep(3000);
		if(allowBtnIsVisible==true) {
			wait.until(ExpectedConditions.elementToBeClickable(allowBtn));
			allowBtn.click();
		}else {
			
		}
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement availabeOnDemandChk = driver.findElement(By.xpath("//label[@class='switch-checkbox']/input/ancestor::a"));
		//wait.until(ExpectedConditions.visibilityOf(availabeOnDemandChk));
		
		//WebElement statusToOnlineDialogBox = driver.findElement(By.xpath("//h4[@class='modal-title ui-dialog-title']"));
		//WebElement confirmBtn = driver.findElement(By.xpath("//div[@id='drupal-modal--footer']//button[text()='Confirm']"));
		boolean checkBox = availabeOnDemandChk.isSelected();
		System.out.println("Check"+checkBox);
		if(checkBox ==!true) {
			System.out.println("RadioBtn is clicked");
		}else {
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView(true)",availabeOnDemandChk);
			js.executeScript("arguments[0].click()",availabeOnDemandChk);
//			wait.until(ExpectedConditions.visibilityOf(statusToOnlineDialogBox));
//			wait.until(ExpectedConditions.visibilityOf(confirmBtn));
//			confirmBtn.click();
		}
		Thread.sleep(7000);
		
		WebElement onDemandNotification = driver.findElement(By.xpath("//div[@class='ondemand__doctor-notification']"));
		wait.until(ExpectedConditions.visibilityOf(onDemandNotification));
		WebElement onDemandAccept = driver.findElement(By.xpath("//a[@data-dialog-title='Accept On-Demand request']"));
		wait.until(ExpectedConditions.visibilityOf(onDemandAccept));
		onDemandAccept.click();
		
		WebElement accReqCnfrmDialogBox = driver.findElement(By.xpath("//h4[@class='modal-title ui-dialog-title']"));
		wait.until(ExpectedConditions.visibilityOf(accReqCnfrmDialogBox));
		WebElement accCnfrmReq = driver.findElement(By.xpath("//div[@class='modal-body ui-dialog-content']//a[text()='Accept']"));
		accCnfrmReq.click();
	}
	
//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}
	

}
