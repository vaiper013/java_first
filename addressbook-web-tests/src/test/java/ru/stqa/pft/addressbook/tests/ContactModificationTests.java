package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goToCon().homeContact();
            app.goToCon().create(new ContactData().
                    withFirstName("Vasiliy").withLastName("Bochkarev").withAddress("Koroleva13").withAllPhones("7777777").withEmail("vaipermail@rambler.ru"));
        }
    }

    @Test
    public void ContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstName("Vasiliy013").withLastName("Inanov").withAddress("Gagarina5")
                .withAllPhones("+78917").withEmail("vaiper@rambler.ru");
        app.goToCon().homeContact();
        app.goToCon().modify(contact, true);
        assertThat(app.goToCon().contactCount(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}






//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//before.remove(before.size() -1);
//before.add (contact);
//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//int index = before.size() - 1;
//Comparator<? super ContactData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byid);
//        after.sort(byid);
//assertEquals(after.size(), before.size());

