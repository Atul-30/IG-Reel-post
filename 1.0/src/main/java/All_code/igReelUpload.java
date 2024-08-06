package All_code;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class igReelUpload {

	static WebDriver driver=new ChromeDriver();
	static Duration timeout = Duration.ofSeconds(200);
    static WebDriverWait wait=new WebDriverWait(driver,timeout);
    
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\chromedriver.exe");
		loginInstagram();
	}
	
	public static void loginInstagram() throws InterruptedException
	{
        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']"))).sendKeys("insta_store_7");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']"))).sendKeys("Atul@1234");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();
	}
	
	public static void fetchReelNameAndCaption()
	{
		
	}
}
