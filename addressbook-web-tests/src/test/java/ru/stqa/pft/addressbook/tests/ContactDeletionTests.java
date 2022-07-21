package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    app.getContactHelper().homeContact();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().dialogAccept();
  }
}
