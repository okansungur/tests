package com.example.myselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MyseleniumApplication {
    protected final static String url = "https://www.google.com/";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D://Apps//chromedriver_win32//chromedriver.exe");
        getSampleWeb("http://localhost:8888/simple/showLogin");
        //getChrome("https://www.google.com/");
    }

    static void getSampleWeb(String url) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(url);
        driver.findElement(By.name("email")).sendKeys("john@sss.com");
        WebElement text = driver.findElement(By.id("password")); text.sendKeys("123");
        text.click();
        text.submit();
        Thread.sleep(1000);

        WebElement  webElement= driver.findElement(By.cssSelector("a[href='/simple/ships']"));
        webElement.click();
        System.out.println("Title => "+driver.getTitle());
        Thread.sleep(500);
        driver.navigate().back();



        Thread.sleep(1000);
        WebElement  webElementStudent= driver.findElement(By.cssSelector("a[href='/simple/student']"));
        webElementStudent.click();
        Thread.sleep(1000);
        WebElement  webElementStudent6= driver.findElement(By.cssSelector("a[href='/simple/student/6']"));
        webElementStudent6.click();
        Thread.sleep(1000);
        driver.navigate().back();

        Thread.sleep(1000);
        WebElement  webElementShow= driver.findElement(By.cssSelector("a[href='/simple/showStudent']"));
        webElementShow.click();
        Thread.sleep(1000);
        driver.findElement(By.name("studentName")).sendKeys("deneme");
        Thread.sleep(1000);
        driver.findElement(By.name("studentAddress")).sendKeys("deneme");
        Thread.sleep(1000);

        WebElement addStudent = driver.findElement(By.name("age"));
        addStudent.sendKeys("44");

        addStudent.click();
        addStudent.submit();
        Thread.sleep(1000);

        WebElement  webElementDelete= driver.findElement(By.cssSelector("a[href='/simple/deleteStudent/7']"));
        webElementDelete.click();
        Thread.sleep(1000);

        WebElement  webElementMain= driver.findElement(By.cssSelector("a[href='/simple/main']"));
        webElementMain.click();
        Thread.sleep(1000);

        WebElement  webElementencrypt= driver.findElement(By.cssSelector("a[href='/simple/encyrpt']"));

        webElementencrypt.click();
        Thread.sleep(1000);
        System.out.println("getTitle => "+driver.getTitle());
        System.out.println("getCurrentUrl => "+driver.getCurrentUrl());
       // System.out.println("getPageSource => "+driver.getPageSource());
        driver.navigate().back();
        Thread.sleep(1000);
        driver.navigate().refresh();

        Thread.sleep(1000);
        WebElement m = driver.findElement (By.xpath ("//div[contains(@class, 'col-sm-12')]"));
        String findComodo=m.getText();
        System.out.println("Find Comodo => "+findComodo);
        String myArray[]= findComodo.split("\\.");

        String result=myArray[1];
        System.out.println(" The text you are looking for is => "+result);

        //driver.navigate().forward();
       // System.out.println("isElementPresent: " + driver.findElements(By.xpath("//input[@title='Search']")).size());
        //WebElement  theElement= driver.findElement(By.id("Div1"));



        /*
        WebDriver driver = new ChromeDriver();
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		EventHandler handler = new EventHandler();
		eventDriver.register(handler);
		eventDriver.get("https://toolsqa.com/automation-practice-switch-windows/");
		WebElement element = eventDriver.findElement(By.id("target"));
		element.click();

		...
		public class EventHandler implements WebDriverEventListener{
		public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		public void afterClickOn(WebElement arg0, WebDriver arg1) {
		public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        ...
        */



    }

    static void getChrome(String url) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(url);
        driver.findElement(By.name("q")).sendKeys("YouTube");
        WebElement searchIcon = driver.findElement(By.name("btnK"));
        searchIcon.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript("alert('" + "--Hi--!" + "')");
        Thread.sleep(3000);
        System.out.println("-----------------------------------------------");
        driver.switchTo().alert().accept();
        String innerText = js.executeScript("return document.documentElement.innerText;").toString();
        //js.executeScript("window.scrollBy(0,800)");
        //js.executeScript("document.body.style.transform='scale(0.5)';");
        //Or robot class can be used here
        System.out.println(innerText);
        //File screenshot = searchIcon.getScreenshotAs(OutputType.FILE);
        /*
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000))
                .takeScreenshot(driver);
                //add pom.xml
    */

    }

}