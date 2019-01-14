package com.ch.spider.exception;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class phantomJsException {
    public static boolean ElementExist(WebDriver driver, By Locator)
    {
        try
        { driver.findElement( Locator );
            return true;
        }
        catch(org.openqa.selenium.NoSuchElementException ex)
        {
            return false;
        }
    }

    public static boolean WebElementExist(WebElement driver, By Locator)
    {
        try
        { driver.findElement( Locator );
            return true;
        }
        catch(org.openqa.selenium.NoSuchElementException ex)
        {
            return false;
        }
    }

}
