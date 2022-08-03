package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class NewcontactCreationTests extends TestBase {

    @Test
    public void testNewcontactCreation() throws Exception {
        app.getContactHelper().homeContact();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() +1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);

        Comparator<? super ContactData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byid);
        after.sort(byid);
        Assert.assertEquals(before,after);
            }
}

//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
