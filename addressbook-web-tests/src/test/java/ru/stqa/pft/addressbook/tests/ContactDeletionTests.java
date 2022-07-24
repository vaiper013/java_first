package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    app.getContactHelper().homeContact();
    if (! app.getContactHelper().isThereAConact()) {
      app.getContactHelper().createContact(new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().dialogAccept();
  }
}
