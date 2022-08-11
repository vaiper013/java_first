package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;




public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        app.goToCon().homeContact();
        if (app.goToCon().all().size() == 0) {
            app.goToCon().create(new ContactData().
                    withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withAllEmail("vaipermail@rambler.ru"));
        }
    }

    @Test
    public void testsContactPhones() {
        app.goToCon().homeContact();
        ContactData contactPhones = before.iterator().next();
        ContactData contacInfoFromEditForm = app.contacts().InfoFromEditForm(contact);
    }
}
