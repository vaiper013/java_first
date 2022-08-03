package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void ContactModification() {
        app.getContactHelper().homeContact();
        if (!app.getContactHelper().isThereAConact()) {
            app.getContactHelper().createContact(new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Vasiliy013", "Inanov", "+78917", "vaiper@rambler.ru");
        app.getContactHelper().fillConactForm(contact);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add (contact);

        Comparator<? super ContactData> byid=(g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
        before.sort(byid);
        after.sort(byid);
        Assert.assertEquals(before,after);

    }

    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


}
