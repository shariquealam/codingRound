import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import com.sun.javafx.PlatformUtil;

import java.util.List;
import org.openqa.selenium.By;

public class HotelBookingTest extends CommonMethods{

    WebDriver driver = new ChromeDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    //Need to add Constructor and declear PageFactory.initElements(driver,this); 
    public HotelBookingTest() {

    	//This initElements method will create all WebElements
    	PageFactory.initElements(driver, this);
    }
    
    
    @Test
    public void shouldBeAbleToSearchForHotels() {
    	
        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");
        
        // Wait till suggestion list displays
        waitFor(2000);
        
        // Select the first element from the list.
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(1).click();
        
        // Chose a Hotel Check-in date
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[1]/a")).click();
        
        waitFor(1000);
        // Chose a Hotel Check-out date
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[2]/a")).click();
        
        
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        // Wait till Screen "searchSummary" is loaded.
        waitTillIsVisiable(driver, "searchSummary");
        
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(driver, By.className("searchSummary")));
        
        driver.quit();

    }

}
