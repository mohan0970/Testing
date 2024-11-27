package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Assessment {

    public static void main(String[] args) throws InterruptedException {
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Actions action = new Actions(driver);
        
        //Navigate to the FitPeo Homepage:
        driver.get("https://www.fitpeo.com/");
 
        // Navigate to the Revenue Calculator Page:
        driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
        Thread.sleep(2000);
   
        //Scroll Down to the Slider section:
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        
        //Adjust the Slider:
        int minVal = 0;
        int maxVal = 2000;
        int snapPoint = 820; 
        WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));
        int sliderWidth = slider.getSize().width;
        double percentage = (double) (snapPoint - minVal) / (maxVal - minVal);
        int xOffset = (int) Math.round(sliderWidth * percentage);
        action.moveToElement(slider).clickAndHold().moveByOffset(xOffset - (sliderWidth / 2), 0).release().perform();
        Thread.sleep(3000); 
        
        
        // Capture the input value after moving the slider
        String text = driver.findElement(By.xpath("//input[@id=':r0:']")).getAttribute("value");
        System.out.println("Captured Input Value: " + text);
        
        
        //Update the Text Field:
       WebElement inputField = driver.findElement(By.xpath("//input[@id=':r0:']"));
       inputField.clear();
       inputField.click();
       inputField.sendKeys(Keys.CONTROL + "a");
       inputField.sendKeys(Keys.BACK_SPACE);
       inputField.sendKeys("560");
       inputField.sendKeys(Keys.TAB);
       Thread.sleep(2000);
       
       //Validate Slider Value:
       //String sliderValue = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']")).getAttribute("value");
       //System.out.println("Slider Value is "+sliderValue);
       
      //Select CPT Codes:
      driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
   
      driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
     
      driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
  
      driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
      Thread.sleep(2000);
      
      //Validate Total Recurring Reimbursement:
      String totalRecurringReimbursement = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]")).getText();
      System.out.println("Total Recurring Reimbursement:"+totalRecurringReimbursement);

      
      //Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.
      String expectedValue = "$110700";
      Assert.assertEquals(totalRecurringReimbursement,expectedValue );

    


       

       
    }
}
