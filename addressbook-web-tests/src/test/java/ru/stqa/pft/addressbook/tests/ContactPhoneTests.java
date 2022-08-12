package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
        Contacts before = app.goToCon().all();
        ContactData contactPhones = before.iterator().next();
        ContactData contacInfoFromEditForm = app.goToCon().infoFromEditForm(contactPhones);

        assertThat(contactPhones.getHomePhone(), equalTo(cleaned(contacInfoFromEditForm.getHomePhone())));
        assertThat(contactPhones.getMobilePhone(), equalTo(cleaned(contacInfoFromEditForm.getMobilePhone())));
        assertThat(contactPhones.getWorkPhone(), equalTo(cleaned(contacInfoFromEditForm.getWorkPhone())));
    }

    public String cleaned (String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
