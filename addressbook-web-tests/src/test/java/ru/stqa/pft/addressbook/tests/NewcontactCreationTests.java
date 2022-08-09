package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewcontactCreationTests extends TestBase {

    @Test
    public void testNewcontactCreation() throws Exception {
        app.goToCon().homeContact();
        Set<ContactData> before = app.goToCon().all();
        ContactData contact = new ContactData().
                withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru");
        app.goToCon().create(contact);
        Set<ContactData> after = app.goToCon().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}

//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//Comparator<? super ContactData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byid);
//        after.sort(byid);
