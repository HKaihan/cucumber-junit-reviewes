package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {


    /*
    this method accept int (int seconds) and
    execute Threda.sleep method for given
     */
    public static void sleep (int second){
        second *=1000;
        try {
            Thread.sleep(second);
        }catch (InterruptedException e){

        }
    }

    public static void switchWindowAndVerify( String expectedInUrl, String expectedInTitle){

        //4. Create a logic to switch to the tab where Etsy.com is open
        //return and store all the window in a set
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();


        for (String each : allWindowHandles) {
            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL "+Driver.getDriver().getCurrentUrl());
            if(Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }

        //5. Assert: Title contains “Etsy”
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    //TC #3: Create utility method
    //1. Create a new method for title verification
    //2. Create a method to make title verification logic re-usable
    //3. When method is called, it should simply verify expected title with actual
    //title
    //Method info:
    //• Name: verifyTitle()
    //• Return type: void
    //• Arg1: WebDriver
    //• Arg2: String expectedTitle

    public static void verifyTitle( String expectedTitle){

        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void verifyTitle_contains( String expectedTitle){

        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle)) ;

    }

    /*
    This method accepts WebElement target and waits
    for the webElement to not to be displayed on the page
     */

    public static void waitForInvisibilityOf (WebElement target){ //target = webElement

        // create the object of WebDriverWait class and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        //use the wait object with the proper syntax to create explicit wait conditions
        wait.until(ExpectedConditions.invisibilityOf(target));
    }

    /*
    This method accepts String title and waits
    for the title to contain given String value.
     */
    public void waitForTitleContains (String title){
        // create the object of WebDriverWait class and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        //use the wait object with the proper syntax to create explicit wait conditions
        wait.until(ExpectedConditions.titleContains(title));

    }


    /*
    This method accepts a dropdown element and returns a List<String> that contains all options values as String

     */

    public static List<String> dropDownOptions_as_STRING (WebElement dropDownElement){
        Select month = new Select(dropDownElement);
        //Storing all the ACTUAL options into a List of webElements
        List<WebElement>actualMonth_as_WEBELEMENT = month.getOptions();

        //Creating an empty list of String to store ACTUAL <options> as String
        List<String> actualMonth_as_String = new ArrayList<>();

        //Looping through the List<WebElement>, getting all options's texts, and storing them into List String
        for (WebElement each : actualMonth_as_WEBELEMENT) {
            actualMonth_as_String.add(each.getText());

        }

        return actualMonth_as_String;
    }

}
