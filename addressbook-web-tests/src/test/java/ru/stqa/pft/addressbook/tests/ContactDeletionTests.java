package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    app.getGroupHelper().homeContact();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteContact();
    app.getGroupHelper().dialogAccept();
  }
}
