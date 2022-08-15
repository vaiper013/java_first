package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
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

        assertThat(contactPhones.getAllPhones(), equalTo(mergePhones(contacInfoFromEditForm)));
        assertThat(contactPhones.getAllAddress(), equalTo(mergeAddress(contacInfoFromEditForm)));
        assertThat(contactPhones.getAllEmail(), equalTo(mergeEmail(contacInfoFromEditForm)));

    }




    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getFax())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeAddress(ContactData contact) {
        return contact.getAllAddress();
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getAllEmail())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactTests::cleaned)
                .collect(Collectors.joining("\n"));

    }




    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }






}


//contact.getAllEmail