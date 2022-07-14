package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver wd;
    private  NavigationHelper navigationHelper;
    private  GroupHelper groupHelper;

    private SessionHelper sessionHelper;

    public void init() {
       wd = new FirefoxDriver();
       wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       wd.get("http://localhost/addressbook/group.php");
       groupHelper = new GroupHelper(wd);
       navigationHelper = new NavigationHelper(wd);
       sessionHelper = new SessionHelper(wd);
       sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }



    public void gotoAddNewContact() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void fillConactForm(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(contactData.getAllPhones());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactData.getAllEmail());
      wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void returnToContactPage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void homeContact() {
      wd.findElement(By.linkText("home")).click();
    }

    public void deleteContact() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void dialogAccept() {
        wd.switchTo().alert().accept();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
