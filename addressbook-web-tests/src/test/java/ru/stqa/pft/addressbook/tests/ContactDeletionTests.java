package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    app.homeContact();
    app.getGroupHelper().selectGroup();
    app.deleteContact();
    app.dialogAccept();
  }
}
