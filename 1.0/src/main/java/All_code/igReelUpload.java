package All_code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class igReelUpload {

    static WebDriver driver;
    static Duration timeout = Duration.ofSeconds(200);
    static WebDriverWait wait;
    static Boolean check = true;
    static int num;
    static String videoLink;
    static String caption;

    public static void main(String[] args) throws InterruptedException, IOException {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\mozila.exe");

//            driver = new ChromeDriver();
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, timeout);
            loginInstagram();
            fetchReelNameAndCaption();
            selectReelandShareReel();
            Thread.sleep(5000);
            driver.quit();
    }

    public static void loginInstagram() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']"))).sendKeys("insta_store_7");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']"))).sendKeys("Atul@1234");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))).click();
    }

    public static void fetchReelNameAndCaption() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\Task list.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");

        Row rowLink;
        do {
            num++;
            rowLink = sheet.getRow(num);
            Cell cellStatus = rowLink.getCell(3);
            String status = cellStatus.getStringCellValue();
            if (!status.equals("Done")) {
                check = false;
            }
        } while (check);

        Cell cellLink = rowLink.getCell(1);
        videoLink = cellLink.getStringCellValue();
        System.out.println(videoLink);

        Cell cellCaption = rowLink.getCell(2);
        caption = cellCaption.getStringCellValue();
        System.out.println(caption);

        Cell cell = rowLink.getCell(3);
        cell.setCellValue("Done");

        // Write the changes to the file
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\atult\\git\\IG-Reel-post\\1.0\\src\\main\\resources\\Task list.xlsx")) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        workbook.close();
        fis.close();
    }

    public static void selectReelandShareReel() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Create']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Post']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Select from computer']"))).click();
        
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(2000);

            StringSelection stringSelection = new StringSelection(videoLink);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            // Paste the string (simulate Ctrl+V)
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Press Enter (simulate pressing the Enter key)
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[1]/div/div[3]/div/div/div/div/div/div/div/div[2]/div[1]/div/div/div/div[1]/div/div[2]/div/button/div"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='9:16']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Next']"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Next']"))).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Write a caption...']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Write a caption...']"))).sendKeys(caption);
        System.out.println(caption);
    }
        
    
}
