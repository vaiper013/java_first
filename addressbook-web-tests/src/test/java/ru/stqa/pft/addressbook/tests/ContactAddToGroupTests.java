package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroupTests extends TestBase{
    private final static String TEST_GROUP_NAME = "new_group";

    @BeforeMethod
    public void ensurePrecondition() {
        app.goToCon().homeContact();
        Groups groups = app.db().groups();
        if (groups.size() == 0 || !groupExists(groups, TEST_GROUP_NAME)) {
            app.goTo().GroupPage();
            app.Group().create(new GroupData().withName(TEST_GROUP_NAME));
            groups = app.db().groups();
        }

        if (!contactWithoutNGroupExists(app.db().contacts(), TEST_GROUP_NAME)) {
            app.goToCon().create(new ContactData()
                    .withFirstName("Vasiliy")
                    .withLastName("Bochkarev")
                    .withAddress("Koroleva13")
                    .withAllPhones("7777777")
                    .withEmail("vaipermail@rambler.ru"));
        }
    }

    @Test
    public void testContactAddingToGroup() {
        app.goToCon().homeContact();
        Groups groups = app.db().groups();
        GroupData groupData = getGroupByName(groups, TEST_GROUP_NAME);
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            Groups contactGroups =  contact.getGroups();
            if (!groupExists(contactGroups, TEST_GROUP_NAME)) {
                app.goToCon().addContactToGroup(contact, groupData);
                app.goToCon().homeContact();
            }
            GroupData afterGroup = app.db().groupById(groupData.getId());
            ContactData contactWithGroup = app.db().contactById(contact.getId());
            Assert.assertTrue(contactWithGroup.getGroups().contains(afterGroup));
            Assert.assertTrue(afterGroup.getContacts().contains(contactWithGroup));
        }
    }

    private boolean groupExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private GroupData getGroupByName(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    private boolean contactWithoutNGroupExists(Contacts contacts, String name) {
        for (ContactData contact : contacts) {
            if (!groupExists(contact.getGroups(), name)) {
                return true;
            }
        }
        return false;
    }

    private boolean contactsExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name) && group.getContacts().size() > 0) {
                return true;
            }
        }
        return false;
    }
}

/*if (!contactsExists(groups, TEST_GROUP_NAME)) {
            app.goToCon().create(new ContactData()
                    .withFirstName("Vasiliy")
                    .withLastName("Bochkarev")
                    .withAddress("Koroleva13")
                    .withAllPhones("7777777")
                    .withEmail("vaipermail@rambler.ru")
                    .inGroup(getGroupByName(groups, TEST_GROUP_NAME)));
        }*/