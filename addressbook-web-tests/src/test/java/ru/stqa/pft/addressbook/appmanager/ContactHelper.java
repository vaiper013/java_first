package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillConactForm(ContactData contactData) {
        type("firstname", contactData.getFirstName());
        type("lastname", contactData.getLastName());
        type("home", contactData.getAllPhones());
        type("email", contactData.getAllEmail());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void returnToContactPage() { click(By.linkText("home page")); }

    public void homeContact() { click(By.linkText("home")); }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']")); }

    public void dialogAccept() { wd.switchTo().alert().accept(); }

    public void selectContact() { click(By.name("selected[]"));
    }

    public void initContactModification () { click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() { click(By.name("update"));
    }


    public void createContact(ContactData contact) {
        click(By.linkText("add new"));
        fillConactForm (contact);
        returnToContactPage();
    }
}