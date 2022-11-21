package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
        type("email", contactData.getEmail());
        type("address", contactData.getAddress());
        //attach(By.name("photo"), contactData.getPhoto());
        click(By.xpath("//div[@id='content']/form/input[21]"));

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() ==1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next()
                        .getName());
            }
            else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
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
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']",id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String hometwo = wd.findElement(By.name("phone2")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String emailtwo = wd.findElement(By.name("email2")).getAttribute("value");
        String emailthree = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
                withHomePhone(home).withMobilePhone(mobile).withHometwoPhone(hometwo).withWorkPhone(work).withAddress(address)
                .withEmail(email).withEmailtwo(emailtwo).withEmailthree(emailthree);



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
            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCacshe.add( new ContactData().withId(id).withFirstName(cells.get(2).getText()).withLastName(cells.get(1).getText())
                    .withAllPhones(allPhones).withEmail(allEmail).withAddress(address));
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

//  public void initContactModificationById(int id) {
//        wd.findElement(By.xpath("//input[contains(@id,'"+id+"')]/../..//img[@alt='Edit']")).click();
//    }

//public Contacts all() {
//            if (contactCacshe !=null) {
//                return new Contacts (contactCacshe);
//            }
//        contactCacshe = new Contacts();
//        List<WebElement> elements = wd.findElements(By.name("entry"));//(By.cssSelector("tr[name=entry]"))
//        for (WebElement element : elements) {
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            List<WebElement> cells = element.findElements(By.tagName("td"));
//            contactCacshe.add( new ContactData().withId(id).withFirstName(cells.get(2).getText()).withLastName(cells.get(1).getText()));
//        }
//        return new Contacts(contactCacshe);
//    }
//String [] phones = cells.get(5).getText().split("\n");
//            contactCacshe.add( new ContactData().withId(id).withFirstName(cells.get(2).getText()).withLastName(cells.get(1).getText())
//                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
//        }