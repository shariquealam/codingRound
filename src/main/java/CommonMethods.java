import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sun.javafx.PlatformUtil;

public class CommonMethods {
	
	// Method to wait till an element is visiable.It will try for max 10 sec.
    public void waitTillIsVisiable(WebDriver driver, String elementName) {
    	try {
    		int maxLoopCount = 10;
        	int totalSeconds = 0;
        	
        	while ( totalSeconds <= maxLoopCount) {
        		
        		if ( isElementPresent(driver, By.className(elementName)) == true ) {
            		break;
            	}
        		
        		try {
                    // thread to sleep for 1000 milliseconds
                    Thread.sleep(1000);
                 } catch (Exception e) {
                    System.out.println(e);
                 }
        		
        		totalSeconds = + 1;
        	}
        } catch (NoSuchElementException e) {
        	
        }
    	
    }

    public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
    
 // Change If conditions to else If
    public void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
        }
        else if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        else if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
	
	
}