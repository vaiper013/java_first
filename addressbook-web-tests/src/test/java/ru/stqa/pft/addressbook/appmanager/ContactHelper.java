package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//input[contains(@id,'"+id+"')]/../..//img[@alt='Edit']")).click();
    }



    public void submitContactModification() {
        click(By.name("update"));
    }


    public void create(ContactData contact) {
        click(By.linkText("add new"));
        fillConactForm(contact);
        contactCacshe = null;
        returnToContactPage();
    }



    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillConactForm(contact);
        submitContactModification();
        contactCacshe = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        dialogAccept();
        contactCacshe = null;
        homeContact();
    }

    public int contactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCacshe = null;

    public Contacts all() {
            if (contactCacshe !=null) {
                return new Contacts (contactCacshe);
            }
        contactCacshe = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));//(By.cssSelector("tr[name=entry]"))
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            contactCacshe.add( new ContactData().withId(id).withFirstName(cells.get(2).getText()).withLastName(cells.get(1).getText()));
        }
        return new Contacts(contactCacshe);
    }
}
//ContactData contact = new ContactData().withId(id).withLastName(cells.get(2).getText()).withFirstName(cells.get(1).getText());
//int id = element.findElement(By.tagName("input")).getAttribute("value");
//List<WebElement> cells = element.findElements(By.tagName("td"));
//String[] elems = element.getText().split("\\s"); // VARIANT A
//ContactData contact = new ContactData(id, elems[1], elems[0], null, null); // VARIANT A
//System.out.println(cells.get(1).getText());
//System.out.println("result:" + element.getText());
//ContactData contact = new ContactData(id, cells.get(2).getText(), cells.get(1).getText(), null, null);
//String name = element.getText();
//public void initContactModification(int index) {

// wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
//}

//   public void deleteCon(int index) {
//        selectContact(index);
//        deleteContact();
//        dialogAccept();
//        homeContact();
//    }
//public void selectContact(int index) {
//        wd.findElements(By.name("selected[]")).get(index).click();
//    }
//    public List<ContactData> ListCon() {
//        List<ContactData> contacts = new ArrayList<ContactData>();
//        List<WebElement> elements = wd.findElements(By.name("entry"));//(By.cssSelector("tr[name=entry]"))
//        for (WebElement element : elements) {
//            //String name = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            List<WebElement> cells = element.findElements(By.tagName("td"));
//            contacts.add( new ContactData().withId(id).withFirstName(cells.get(2).getText()).withLastName(cells.get(1).getText()));
//        }
//        return contacts;
//    }