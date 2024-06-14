package com.utls;


import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

public class baseActions  {
    public Properties properties;
    WebDriver driver;
    String path = "src/test/resources/config.properties";


    public String getProperty(String key)
    {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(path);
            properties.load(input);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }

        return properties.getProperty(key);

    }

    public void validateElementPresent(By by,WebDriver driver)
    {
        WebElement element = driver.findElement(by);
        boolean elementPresent = element.isDisplayed();
        assertTrue(elementPresent,"The required element is not present");
    }
    public void highlightElement(WebElement element,WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Save the original style of the element
        String originalStyle = element.getAttribute("style");
        // Set the new style
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red; background-color: yellow;");
        try {
            // Keep the new style for a while
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Revert to the original style
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    public void clickOnElement(By by,WebDriver driver)
    {
        try
        {
            WebElement element = driver.findElement(by);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for the button to be clickable
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void enterText(String text,By by,WebDriver driver)
    {

            WebElement element = driver.findElement(by);
            element.click();
            element.clear();
            element.sendKeys(text);


    }

    public String currentDateTime()
    {
        LocalDateTime now = LocalDateTime.now();

        // Define the format for date and time in yyyymmddhh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");

        // Format the current date and time
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }


    public static void captureScreenshot(WebDriver driver, String docName) throws FileNotFoundException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Call getScreenshotAs method to create an image file
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String screenshotsDir = "src/test/Screenshots/";
        String screenshotsPathWordDoc = screenshotsDir + docName+".docx";

        try {
            // Create screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get(screenshotsDir));

            // Initialize or load the Word document
            XWPFDocument document;
            Path screenshotsDocumentPath = Paths.get(screenshotsPathWordDoc);
            if (!Files.exists(screenshotsDocumentPath)) {
                // Create a new document if it doesn't exist
                document = new XWPFDocument();
            } else {
                // Load existing document
                document = new XWPFDocument(Files.newInputStream(screenshotsDocumentPath));
            }

            // Add a paragraph to the document
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            // Add screenshot to the document
            InputStream imageStream = new FileInputStream(file);
            run.addPicture(imageStream, XWPFDocument.PICTURE_TYPE_JPEG, "screenshot.jpg", Units.toEMU(500), Units.toEMU(250));
            imageStream.close();

            // Write the document back to filesystem
            FileOutputStream fos = new FileOutputStream(screenshotsPathWordDoc);
            document.write(fos);
            fos.close();

            // Close the document
            document.close();

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }
    }