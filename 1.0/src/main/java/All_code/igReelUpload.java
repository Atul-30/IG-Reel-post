package All_code;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.sl.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class igReelUpload {

//	static WebDriver driver=new ChromeDriver();
//	static Duration timeout = Duration.ofSeconds(200);
//    static WebDriverWait wait=new WebDriverWait(driver,timeout);
    static Boolean check= true;
    static int num;
	public static void main(String[] args) throws InterruptedException, IOException {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\chromedriver.exe");
//		loginInstagram();
		fetchReelNameAndCaption();
	}
	
	public static void loginInstagram() throws InterruptedException
	{
//        driver.get("https://www.instagram.com/");
//        driver.manage().window().maximize();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']"))).sendKeys("insta_store_7");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']"))).sendKeys("Atul@1234");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();
	}
	
	public static void fetchReelNameAndCaption() throws IOException
	{
		
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\Task list.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");
        Row rowLink = sheet.getRow(1);
		do
		{
			num++;
			rowLink = sheet.getRow(num);
			Cell cellStatus = rowLink.getCell(3);
	        String status = cellStatus.getStringCellValue();
			if(!status.equals("Done"))
			{
				check=false;
			}
		}
		while(check==true);
		
        Cell cellLink = rowLink.getCell(1);
        String videoLink = cellLink.getStringCellValue();
        System.out.println(videoLink);
        
        Cell cellCaption = rowLink.getCell(2);
        String caption = cellCaption.getStringCellValue();
        System.out.println(caption);
        
        Cell cell = rowLink.getCell(3);
        cell.setCellValue("Done");

        // Write the changes to the file
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\Task list.xlsx")) 
        {
            workbook.write(fos);
        }
        
	    catch (IOException e) 
        {
	        e.printStackTrace();
	    }
        workbook.close();
        fis.close();
	}
}
