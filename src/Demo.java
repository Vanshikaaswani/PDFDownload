import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Demo {
	
	public static void main(String[] args) {
	/*	System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Downloads\\Assessment\\Demo\\Driver\\chromedriver.exe");
		
		ChromeOptions options=new ChromeOptions();
		
		options.setBinary("C:\\Users\\hp\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
		
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		
		*/
		
		// Set download directory
        String downloadDir = "C:\\Downloads"; // Change this path as needed

		 // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Downloads\\Assessment\\chromedriver.exe");

        // Set download directory
        ChromeOptions options = new ChromeOptions();
        options.addArguments("download.default_directory=C:\\Downloads"); // Change this path as needed

        // Create a new instance of the ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);
        
        
        
        try {
            // Navigate to the IITR website
            driver.get("https://www.iitr.ac.in/dosw/pages/Notices.html");

            // Find the PDF download link
            WebElement pdfLink = driver.findElement(By.cssSelector("a[href$='.pdf']"));

            // Get the URL of the PDF file
            String pdfUrl = pdfLink.getAttribute("href");

            // Download the PDF file using JavaScript to trigger the download
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].setAttribute('download', ''); arguments[0].click();", pdfLink);

            // Wait for the download to complete
            waitForFileDownload(downloadDir, "your_filename_here.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the WebDriver
            driver.quit();
        }
    }

    private static void waitForFileDownload(String downloadDir, String filename) {
        File file = new File(downloadDir + "\\" + filename);
        int timeout = 60; // Timeout in seconds
        int waited = 0;
        while (!file.exists() && waited < timeout) {
            try {
                Thread.sleep(1000); // Wait for 1 second
                waited++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Check if the file exists after the download
        if (file.exists()) {
            System.out.println("PDF downloaded successfully.");
            // Add further actions here, such as opening or processing the file
        } else {
            System.out.println("PDF download failed or timed out.");
        }
    }
}

       /* try {
            // Navigate to the MoRTH website
            driver.get("https://www.iitr.ac.in/dosw/pages/Notices.html");

            // Find the link to download the latest PDF
            WebElement pdfLink = driver.findElement(By.cssSelector("a[href$='.pdf']"));

            // Get the URL of the PDF file
            String pdfUrl = pdfLink.getAttribute("href");

            // Download the PDF file using JavaScript to trigger the download
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].setAttribute('download', ''); arguments[0].click();", pdfLink);

            // Wait for the download to complete by checking if the file exists in the download directory
            File downloadedFile = new File("C:\\Downloads\\" + "your_filename_here.pdf"); // Change the filename as needed
            int timeout = 60; // Timeout in seconds
            int waited = 0;
            while (!downloadedFile.exists() && waited < timeout) {
                Thread.sleep(1000); // Wait for 1 second
                waited++;
            }

            // Check if the file exists after the timeout
            if (downloadedFile.exists()) {
                System.out.println("PDF downloaded successfully.");
            } else {
                System.out.println("PDF download failed or timed out.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the WebDriver
            driver.quit();
        }
    }
}*/