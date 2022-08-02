package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public void homeContact() {
        click(By.linkText("home"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void dialogAccept() {
        wd.switchTo().alert().accept();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    public void createContact(ContactData contact) {
        click(By.linkText("add new"));
        fillConactForm(contact);
        returnToContactPage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));//By.name("entry") (By.cssSelector("tr[name=entry]"))
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            ContactData contact = new ContactData(id, cells.get(2).getText(), cells.get(1).getText(), null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
//int id = element.findElement(By.tagName("input")).getAttribute("value");
//List<WebElement> cells = element.findElements(By.tagName("td"));
//String[] elems = element.getText().split("\\s"); // VARIANT A
//ContactData contact = new ContactData(id, elems[1], elems[0], null, null); // VARIANT A
//System.out.println(cells.get(1).getText());
//System.out.println("result:" + element.getText());