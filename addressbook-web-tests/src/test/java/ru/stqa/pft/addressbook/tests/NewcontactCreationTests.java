package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewcontactCreationTests extends TestBase {

  @Test
  public void testNewcontactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper ().fillConactForm (new ContactData("Vasiliy", "Bochkarev", "7777777", "vaipermail@rambler.ru"));
    app.getContactHelper().returnToContactPage();
  }
}
