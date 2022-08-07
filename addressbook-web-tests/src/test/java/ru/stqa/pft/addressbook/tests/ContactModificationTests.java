package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goToCon().homeContact();
        if (app.goToCon().ListCon().size() == 0) {
            app.goToCon().create(new ContactData().
                    withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru"));
        }
    }

    @Test
    public void ContactModification() {
        List<ContactData> before = app.goToCon().ListCon();
        int index = before.size() - 1;
        ContactData contact = new ContactData().
                withId(before.get(index).getId()).withFirstName("Vasiliy013").withLastName("Inanov").withAllPhones("+78917").withAllEmail("vaiper@rambler.ru");
        app.goToCon().modify(index, contact);
        List<ContactData> after = app.goToCon().ListCon();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byid);
        after.sort(byid);
        Assert.assertEquals(before, after);


    }
}

    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //before.remove(before.size() -1);
    //before.add (contact);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));



