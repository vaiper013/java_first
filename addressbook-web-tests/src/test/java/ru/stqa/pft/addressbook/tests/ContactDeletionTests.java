package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    app.getContactHelper().homeContact();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAConact()) {
      app.getContactHelper().createContact(new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().dialogAccept();
    app.getContactHelper().homeContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before -1);
    //тест проходит только при наличии одного контакта в списке..
  }
}
