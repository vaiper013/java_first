package ru.stqa.pft.addressbook;
import org.testng.annotations.*;

public class NewcontactCreationTests extends TestBase {

  @Test
  public void testNewcontactCreation() throws Exception {
    gotoAddNewContact();
    fillConactForm(new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
    returnToContactPage();
  }
}
