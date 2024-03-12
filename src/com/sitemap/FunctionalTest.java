package com.sitemap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FunctionalTest {

    public static void main(String[] args) {
        // Open the web browser and navigate to the application
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.dealsdray.com/");
        WebElement usernameField = driver.findElement(By.xpath("//input[@id='mui-1']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='mui-2']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        usernameField.sendKeys("prexo.mis@dealsdray.com");
        passwordField.sendKeys("prexo.mis@dealsdray.com");
        loginButton.click();
        // Identify and click the Order button
        WebElement nav = driver.findElement(By.className("MuiButtonBase-root has-submenu compactNavItem open css-46up3a"));
        nav.click();
        
//        WebElement orderButton = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]"));
//        orderButton.click();

        // Wait for the submenu to be visible (you might need to add a wait here)

        // Identify and click the Orders button
        WebElement submenu = driver.findElement(By.className("submenu"));
        WebElement ordersButton = submenu.findElement(By.xpath("//button[contains(@class,'css-1hytwp4')]"));
        ordersButton.click();

        // Identify and click the Bad Orders button
        WebElement badOrdersButton = submenu.findElement(By.xpath("//button[contains(@class,'css-1hytwp4')][2]"));
        badOrdersButton.click();

        // Identify and click the Add Bulk Orders button
        WebElement addBulkOrdersButton = driver.findElement(By.xpath("//button[contains(@class,'your-add-bulk-orders-class')]"));
        addBulkOrdersButton.click();

        // Close the browser
        driver.quit();
    }
}
