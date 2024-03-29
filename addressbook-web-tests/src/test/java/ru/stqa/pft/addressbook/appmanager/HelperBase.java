package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class HelperBase {
    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd=wd;
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }



    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    public void type(String arbitrary, String contactData) {
                click(By.name(arbitrary));
        wd.findElement(By.name(arbitrary)).clear();
        wd.findElement(By.name(arbitrary)).sendKeys(contactData);
    }
}

//public boolean isThereAGroup() {
//        return isElementPresent(By.name("selected[]"));
//    }
//
//    public boolean isThereAConact() {
//        return isElementPresent(By.name("selected[]"));
//    }