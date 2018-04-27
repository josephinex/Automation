package test.java.com.usm.lab4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Lab4Test {

    private WebDriver webDriver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        webDriver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        if(webDriver!=null) {
            System.out.println("Closing chrome browser");
            webDriver.quit();
        }
    }

    @Test
    public void fillFormTest(){
        webDriver.navigate().to("http://demoqa.com/registration/");
        webDriver.findElement(By.name("first_name")).sendKeys("John");
        webDriver.findElement(By.name("last_name")).sendKeys("Doe");

        webDriver.findElements(By.name("radio_4[]")).get(1).click();

        webDriver.findElements(By.name("checkbox_5[]")).get(0).click();

        Select drpCountry = new Select(webDriver.findElement(By.id("dropdown_7")));
        drpCountry.selectByValue("Moldova");

        Select month = new Select(webDriver.findElement(By.id("mm_date_8")));
        month.selectByValue("3");

        Select day = new Select(webDriver.findElement(By.id("dd_date_8")));
        day.selectByValue("12");

        Select year = new Select(webDriver.findElement(By.id("yy_date_8")));
        year.selectByValue("2011");

        webDriver.findElement(By.id("phone_9")).sendKeys("37312345678910");
        webDriver.findElement(By.id("username")).sendKeys("username");
        webDriver.findElement(By.id("email_1")).sendKeys("username@mail.com");
        webDriver.findElement(By.id("description")).sendKeys("My name is John Doe");
        webDriver.findElement(By.id("password_2")).sendKeys("password1");
        webDriver.findElement(By.id("confirm_password_password_2")).sendKeys("password1");

        webDriver.findElement(By.name("pie_submit")).submit();
    }

    @DataProvider(name = "lotDataProvider")
    public Object[][] dataProvider() {
        return new Object[][] { { "01:00", "03/03/2018", "11:00", "03/04/2018", "$ 9.00" }, { "03:00", "03/03/2018", "10:00", "03/05/2018", "$ 18.00" } };
    }

    @Test(dataProvider = "lotDataProvider")
    public void calculateCost(String entryTime, String entryDate, String exitTime, String exitDate, String result){
        webDriver.navigate().to("http://adam.goucher.ca/parkcalc/index.php");
        Select lot = new Select(webDriver.findElement(By.name("Lot")));
        lot.selectByValue("EP");

        webDriver.findElement(By.id("EntryTime")).clear();
        webDriver.findElement(By.id("EntryTime")).sendKeys(entryTime);

        webDriver.findElements(By.name("EntryTimeAMPM")).get(1).click();

        webDriver.findElement(By.id("EntryDate")).clear();
        webDriver.findElement(By.id("EntryDate")).sendKeys(entryDate);

        webDriver.findElement(By.id("ExitTime")).clear();
        webDriver.findElement(By.id("ExitTime")).sendKeys(exitTime);

        webDriver.findElements(By.name("ExitTimeAMPM")).get(0).click();

        webDriver.findElement(By.id("ExitDate")).clear();
        webDriver.findElement(By.id("ExitDate")).sendKeys(exitDate);

        webDriver.findElement(By.name("Submit")).submit();

        String price = webDriver.findElements(By.className("SubHead")).get(1).findElement(By.tagName("b")).getText();

        Assert.assertEquals(result, price);
    }
}
