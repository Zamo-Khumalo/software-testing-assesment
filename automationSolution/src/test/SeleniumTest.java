package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {

    @Test
    public void SampleTest() throws Exception{
        ArrayList<String> items = new ArrayList<String>();
        System.setProperty("webdriver.chrome.driver","src\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        /* adding products*/
        System.out.println();
        items.add(driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[5]/div[2]/a/div")).getText());
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[5]/div[2]/a/div")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/button")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/button")).click();

        items.add(driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/a/div")).getText());
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/a/div")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/button")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/button")).click();

        /*Check if Items are correct*/
        driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/a/span")).click();

        /*Fix to get elements from cart list*/
        items.remove(driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[1]/div[3]/div[2]/a/div")).getText());
        items.remove(driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[1]/div[4]/div[2]/a/div")).getText());
        assertEquals("Items in the Cart are not the same",items.size(),0);


        /*Fill in personal details*/
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Zamo");
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("Khumalo");
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("2000");
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/form/div[2]/input")).click();


        List<WebElement> element = driver.findElements(By.cssSelector(".inventory_item_price"));
        Double tempTotal=0.0;

        for(WebElement ele:element)
        {
            tempTotal+= Double.parseDouble(ele.getText().substring(1));

        }


        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[5]")).getText();

        Double actualTotal =Double.parseDouble( (driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[5]")).getText()).split("\\$")[1]);


        assertEquals(actualTotal,tempTotal);


        /*Finish order*/
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[8]/a[2]")).click();

        /*Check final screen*/
        String expectedFinal = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        String actualFinal = driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div")).getText();
        assertEquals(expectedFinal,actualFinal);

        driver.close();


    }
}
