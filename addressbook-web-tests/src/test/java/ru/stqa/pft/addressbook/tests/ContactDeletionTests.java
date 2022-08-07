package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goToCon().homeContact();
    if (app.goToCon().ListCon().size() == 0) {
      app.goToCon().create(new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
    }
  }

  @Test
  public void testContactDeletion()  {
    List<ContactData> before = app.goToCon().ListCon();
    int index = before.size() - 1;
    app.goToCon().delete(index);
    List<ContactData> after = app.goToCon().ListCon();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
