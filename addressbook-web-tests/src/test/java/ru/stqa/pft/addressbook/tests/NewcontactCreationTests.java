package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class NewcontactCreationTests extends TestBase {

    @Test
    public void testNewcontactCreation() throws Exception {
        app.getContactHelper().homeContact();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(new ContactData
                ("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() +1);
    }
}
