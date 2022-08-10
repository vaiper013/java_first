package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goToCon().homeContact();
    if (app.goToCon().all().size() == 0) {
      app.goToCon().create(new ContactData().
              withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru"));
    }
  }

  @Test
  public void testContactDeletion()  {
    Contacts before = app.goToCon().all();
    ContactData deletedContact = before.iterator().next();
    app.goToCon().delete(deletedContact);
    assertThat(app.goToCon().contactCount(), equalTo(before.size() -1));
    Contacts after = app.goToCon().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}

//int index = before.size() - 1;
//before.remove(deletedContact);
//Assert.assertEquals(before, after);
//assertEquals(after.size(), before.size() -1);
