package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewcontactCreationTests extends TestBase {

    @Test
    public void testNewcontactCreation() throws Exception {
        app.goToCon().homeContact();
        Contacts before = app.goToCon().all();
        ContactData contact = new ContactData().
                withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru");
        app.goToCon().create(contact);
        Contacts after = app.goToCon().all();
        assertThat(after.size(), equalTo(before.size() +1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }
}







//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//Comparator<? super ContactData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byid);
//        after.sort(byid);
//Assert.assertEquals(before, after);
//Assert.assertEquals(after.size(), before.size() + 1);
//before.add(contact);
//contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());