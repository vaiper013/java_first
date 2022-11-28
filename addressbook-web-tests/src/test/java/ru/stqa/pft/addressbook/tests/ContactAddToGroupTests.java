package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactAddToGroupTests extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        app.goToCon().homeContact();
        app.goToCon().selectContactWithoutGroup();
        if ( app.db().contacts().size()==0 || app.goToCon().all().size() ==0){
            app.goToCon().create(new ContactData()
                    .withFirstName("Vasiliy")
                    .withLastName("Bochkarev")
                    .withAddress("Koroleva13")
                    .withEmail("vaipermail@rambler.ru"));
        }
        if(app.db().groups().size() ==0){
            app.goTo().GroupPage();
            app.Group().create(new GroupData().withName("test2"));
        }
    }
    @Test
    public void testContactAddingToGroup() {
        app.goToCon().homeContact();
        Contacts before = app.db().contacts();
        ContactData toBeAddContact = null;
        for (ContactData contacts : before) {
            if (contacts.getGroups().size() == 0) {
                toBeAddContact = contacts;
                break;
            }
        }
        GroupData group =  app.db().groups().iterator().next();
        app.goToCon().addContactToGroup(toBeAddContact, group);
        app.goToCon().homeContact();

        ContactData contactWithGroup = app.db().contactById(toBeAddContact.getId());
        GroupData afterGroup = app.db().groupById(group.getId());
        Assert.assertTrue(contactWithGroup.getGroups().contains(afterGroup));
        Assert.assertTrue(afterGroup.getContacts().contains(contactWithGroup));
    }
}