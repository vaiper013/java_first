package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goToCon().homeContact();
    if (app.goToCon().ListCon().size() == 0) {
      app.goToCon().create(new ContactData().
              withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru"));
    }
  }

  @Test
  public void testContactDeletion()  {
    Set<ContactData> before = app.goToCon().all();
    ContactData deletedContact = before.iterator().next();
    app.goToCon().delete(deletedContact);
    Set<ContactData> after = app.goToCon().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}

//int index = before.size() - 1;
