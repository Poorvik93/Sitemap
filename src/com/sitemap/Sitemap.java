package com.sitemap;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Sitemap {

    public static void main(String[] args) {
        // List of resolutions and devices
        List<String> devices = Arrays.asList("Desktop", "Mobile");
        List<String> resolutionsDesktop = Arrays.asList("1920x1080", "1366x768", "1536x864");
        List<String> resolutionsMobile = Arrays.asList("360x640", "414x896", "375x667");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("https://www.getcalley.com/page-sitemap.xml");

        // Locate the table containing the links
        WebElement table = driver.findElement(By.id("sitemap"));

        // Identify and collect all links inside the table
        List<WebElement> links = table.findElements(By.tagName("a"));

        // Loop through devices and resolutions
        for (String device : devices) {
            List<String> resolutions = (device.equalsIgnoreCase("Desktop")) ? resolutionsDesktop : resolutionsMobile;

            for (String resolution : resolutions) {
                // Set the window size based on the resolution
                String[] dimensions = resolution.split("x");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                driver.manage().window().setSize(new Dimension(width, height));

                // Visit the links and take screenshots
                int numberOfLinksToVisit = Math.min(5, links.size());
                for (int i = 0; i < numberOfLinksToVisit; i++) {
                    WebElement link = links.get(i);

                    // Click on the link to navigate to the page
                    link.click();

                    // Take a screenshot of the current page
                    takeScreenshot(driver, device, resolution, "link_" + i + "_screenshot.png");

                    // Navigate back to the original page
                    driver.navigate().back();

                    // Re-fetch the links to avoid StaleElementReferenceException
                    table = driver.findElement(By.id("sitemap"));
                    links = table.findElements(By.tagName("a"));
                }
            }
        }

        // Close the browser
        driver.quit();
    }

    private static void takeScreenshot(WebDriver driver, String device, String resolution, String fileName) {
        try {
            // Take screenshot and save it to a file
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);

            // Create folders for each combination of device and resolution
            String folderPath = "C:\\Users\\Poorvikgowda K\\Desktop\\testingscr\\" + device + "/" + resolution + "/";
            File folder = new File(folderPath);
            folder.mkdirs();

            // Create a unique file name with date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
            fileName = "Screenshot-" + dateFormat.format(new Date()) + "_" + fileName;

            // Move the screenshot file to the desired location
            FileHandler.copy(screenshotFile, new File(folder, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
