package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goToCon().homeContact();
        if (app.goToCon().all().size() == 0) {
            app.goToCon().create(new ContactData().
                    withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru"));
        }
    }

    @Test
    public void ContactModification() {
        Set<ContactData> before = app.goToCon().all();
        ContactData modifiedContact = before.iterator().next();
                ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withFirstName("Vasiliy013").withLastName("Inanov").withAllPhones("+78917").withAllEmail("vaiper@rambler.ru");
        app.goToCon().modify(contact);
        Set<ContactData> after = app.goToCon().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(before, after);


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


