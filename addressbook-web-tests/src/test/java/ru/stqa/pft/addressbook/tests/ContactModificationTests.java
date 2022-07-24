package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void ContactModification () {
        app.getContactHelper().homeContact();
        if (! app.getContactHelper().isThereAConact()) {
            app.getContactHelper().createContact(new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper ().fillConactForm (new ContactData("Vasiliy013", "Inanov", "+78917", "vaiper@rambler.ru"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
    }





}
