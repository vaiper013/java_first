package ru.stqa.pft.addressbook;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion()  {
    homeContact();
    selectGroup();
    deleteContact();
    dialogAccept();
  }
}
