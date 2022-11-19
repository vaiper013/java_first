package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goToCon().homeContact();
    if (app.db().contacts().size() == 0) {
      app.goToCon().create(new ContactData().
              withFirstName("Vasiliy").withLastName("Bochkarev").withAddress("Koroleva13").withAllPhones("7777777").withEmail("vaipermail@rambler.ru"));
    }
  }

  @Test
  public void testContactDeletion()  {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goToCon().delete(deletedContact);
    assertThat(app.goToCon().contactCount(), equalTo(before.size() -1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}





