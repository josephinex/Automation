package com.usm.lab5;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Lab5Test {

    private WebDriver webDriver;

    private static Logger Log = LogManager.getLogger(Lab5Test.class);

    private File file = new File("Labs/src/test/resources/application.properties");
    private FileInputStream fileInput;

    private Properties properties = new Properties();

    private String url = null;

    private String username = null;

    private String password = null;


    @BeforeClass
    public void setup() {

        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(fileInput);
            fileInput.close();
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        webDriver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            System.out.println("Closing chrome browser");
            webDriver.quit();
        }
    }

    @Test
    public void loginLogout() {
        webDriver.navigate().to("https://wordpress.com/log-in");

        Log.info("Entering username ");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys(username);

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("password")).sendKeys(password);

        takeScreenshot(webDriver, "enter_password");

        Log.info("Submit ");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[@data-tip-target='me']")).click();

        webDriver.findElement(By.xpath("//*[@class='sidebar__me-signout']/button")).click();
    }

    @Test
    public void createAndPublishPost() {
        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath("//*[@data-post-type='post']//*[@class='sidebar__button']")).click();

        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).clear();
        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).sendKeys("My journey to Paris");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.switchTo().frame(webDriver.findElement(By.id("tinymce-1_ifr")));
        webDriver.findElement(By.xpath("//*[@id='tinymce']")).sendKeys("I want to travel to Paris.");

        webDriver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        takeScreenshot(webDriver, "before_publish");

        By publishButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        webDriver.findElement(publishButton).click();

        By confirmationButton = By.xpath("//*[@class='editor-confirmation-sidebar__action']/button");
        wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        webDriver.findElement(confirmationButton).click();
    }

    @Test
    public void createAndPublishPage() {

        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath("//*[@data-post-type='page']//*[@class='sidebar__button']")).click();

        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).clear();
        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).sendKeys("My journey to Paris");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.switchTo().frame(webDriver.findElement(By.id("tinymce-1_ifr")));
        webDriver.findElement(By.xpath("//*[@id='tinymce']")).sendKeys("I want to travel to Paris.");

        webDriver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By publishButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        webDriver.findElement(publishButton).click();

        takeScreenshot(webDriver, "before_confirm_publish");

        By confirmationButton = By.xpath("//*[@class='editor-confirmation-sidebar__action']/button");
        wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        webDriver.findElement(confirmationButton).click();
    }

    @Test
    public void createPostAndMoveToDraft() {
        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath("//*[@data-post-type='post']//*[@class='sidebar__button']")).click();

        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).clear();
        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).sendKeys("My journey to Paris");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.switchTo().frame(webDriver.findElement(By.id("tinymce-1_ifr")));
        webDriver.findElement(By.xpath("//*[@id='tinymce']")).sendKeys("I want to travel to Paris.");

        webDriver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By publishButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        webDriver.findElement(publishButton).click();

        By confirmationButton = By.xpath("//*[@class='editor-confirmation-sidebar__action']/button");
        wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        webDriver.findElement(confirmationButton).click();

        webDriver.findElement(By.xpath("//*[@class='button web-preview__edit']")).click();

        webDriver.findElement(By.xpath("//*[@class='accordion__header']/button")).click();

        takeScreenshot(webDriver, "before_moving_to_draft");

        By revertToDraft = By.xpath("//*[@class='button edit-post-status__revert-to-draft is-compact']");
        wait.until(ExpectedConditions.elementToBeClickable(revertToDraft));
        webDriver.findElement(revertToDraft).click();
    }

    @Test
    public void createPreviewAndPublishPage() {

        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[@data-post-type='page']//*[@class='sidebar__button']")).click();

        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).clear();
        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).sendKeys("My journey to Paris");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.switchTo().frame(webDriver.findElement(By.id("tinymce-1_ifr")));
        webDriver.findElement(By.xpath("//*[@id='tinymce']")).sendKeys("I want to travel to Paris.");

        takeScreenshot(webDriver, "before_publish");

        webDriver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By previewButton = By.xpath("//*[@class='button editor-ground-control__preview-button']");
        wait.until(ExpectedConditions.elementToBeClickable(previewButton));
        webDriver.findElement(previewButton).click();

        By publishButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        webDriver.findElement(publishButton).click();

        By confirmationButton = By.xpath("//*[@class='editor-confirmation-sidebar__action']/button");
        wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        webDriver.findElement(confirmationButton).click();
    }

    @Test
    public void inviteNewUser() {

        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[@data-tip-target='people']")).click();

        takeScreenshot(webDriver, "invite-page-transition");

        webDriver.findElement(By.xpath("//*[@class='button people-list-section-header__add-button is-compact']")).click();
        webDriver.findElement(By.id("usernamesOrEmails")).sendKeys("joseph.inex@mail-on.us");
        webDriver.findElement(By.xpath("//*[@class='counted-textarea__input form-textarea']")).sendKeys("Hello there");


        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        takeScreenshot(webDriver, "before_sending_invite");

        By sendButton = By.xpath("//*[@class='button form-button is-primary']");
        wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        webDriver.findElement(sendButton).click();
    }

    @Test
    public void editExistingPost() {
        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        takeScreenshot(webDriver, "enter_password");
        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        takeScreenshot(webDriver, "move_to_post_page");
        webDriver.findElement(By.xpath("//*[@data-post-type='post']/a")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By ellipsis = By.xpath("//*[@class='button ellipsis-menu__toggle is-borderless']");
        wait.until(ExpectedConditions.elementToBeClickable(ellipsis));
        webDriver.findElement(ellipsis).click();

        By popup = By.xpath("//*[@class='popover__menu']/a");
        wait.until(ExpectedConditions.elementToBeClickable(popup));
        webDriver.findElement(popup).click();
        takeScreenshot(webDriver, "edit_menu");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[@class='textarea-autosize editor-title__input']")).clear();
        webDriver.findElement(By.xpath("//*[@class='textarea-autosize editor-title__input']")).sendKeys("My journey to London");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        takeScreenshot(webDriver, "edit_page");

        webDriver.switchTo().frame(webDriver.findElement(By.id("tinymce-1_ifr")));
        webDriver.findElement(By.xpath("//*[@id='tinymce']")).sendKeys("I want to travel to London and other european cities.");

        webDriver.switchTo().defaultContent();

        By publishButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        webDriver.findElement(publishButton).click();

        takeScreenshot(webDriver, "update_edited");

        By confirmationButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        webDriver.findElement(confirmationButton).click();
    }

    @Test
    public void createAndDeletePage() {

        webDriver.navigate().to("https://wordpress.com/log-in");
        webDriver.findElement(By.id("usernameOrEmail")).sendKeys("tanyalab");

        webDriver.findElements(By.className("login__form-action")).get(0).findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.id("password")).sendKeys("Abc123456");
        webDriver.findElement(By.xpath("//*[button/@type='submit']")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//*[a/@data-tip-target='my-sites']/a")).click();

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath("//*[@data-post-type='page']//*[@class='sidebar__button']")).click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        takeScreenshot(webDriver, "transition_to_edit");
        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).clear();
        webDriver.findElement(By.xpath("//*[@placeholder='Title']")).sendKeys("My journey to Paris");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.switchTo().frame(webDriver.findElement(By.id("tinymce-1_ifr")));
        webDriver.findElement(By.xpath("//*[@id='tinymce']")).sendKeys("I want to travel to Paris.");

        webDriver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        By publishButton = By.xpath("//*[@class='editor-ground-control__publish-button']/button");
        wait.until(ExpectedConditions.elementToBeClickable(publishButton));
        webDriver.findElement(publishButton).click();

        takeScreenshot(webDriver, "Before_Confirmation");

        By confirmationButton = By.xpath("//*[@class='editor-confirmation-sidebar__action']/button");
        wait.until(ExpectedConditions.elementToBeClickable(confirmationButton));
        webDriver.findElement(confirmationButton).click();

        takeScreenshot(webDriver, "After_Confirm");

        webDriver.findElement(By.xpath("//*[@class='button web-preview__edit']")).click();

        webDriver.findElement(By.xpath("//*[@class='button editor-delete-post__button is-borderless']")).click();

        By confirmToDelete = By.xpath("//*[@class='button is-primary']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmToDelete));

        takeScreenshot(webDriver, "Before_Delete");
        webDriver.findElement(confirmToDelete).click();
    }

    private void takeScreenshot(WebDriver driver, String name) {
        if (TakesScreenshot.class.isInstance(driver)) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File image = File.createTempFile(name + "_", ".png");
                FileUtils.copyFile(scrFile, image);
                System.out.println("Saved " + image.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
