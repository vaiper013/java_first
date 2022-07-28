package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewcontactCreationTests extends TestBase {

    @Test
    public void testNewcontactCreation() throws Exception {
        app.getContactHelper().homeContact();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData
                ("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before +1);
    }
}
