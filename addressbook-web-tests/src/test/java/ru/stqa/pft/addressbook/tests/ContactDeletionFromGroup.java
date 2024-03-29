package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroup extends TestBase {

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
        if (!contactsExists(groups, TEST_GROUP_NAME)) {
            app.goToCon().create(new ContactData()
                    .withFirstName("Vasiliy")
                    .withLastName("Bochkarev")
                    .withAddress("Koroleva13")
                    .withAllPhones("7777777")
                    .withEmail("vaipermail@rambler.ru")
                    .inGroup(getGroupByName(groups, TEST_GROUP_NAME)));
        }
    }

    @Test
    public void testContactDeletionFromGroup() {
        Groups groups = app.db().groups();
        GroupData groupData = getGroupByName(groups, TEST_GROUP_NAME);
        ContactData contact = groupData.getContacts().stream().findFirst().get();
        app.goToCon().delContactFromGroup(contact, groupData);
        app.goToCon().homeContact();
        ContactData contactWithOutGroup = app.db().contactById(contact.getId());
        GroupData afterGroup = app.db().groupById(groupData.getId());
        Assert.assertFalse(contactWithOutGroup.getGroups().contains(afterGroup));
        Assert.assertFalse(afterGroup.getContacts().contains(contactWithOutGroup));
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

    private boolean contactsExists(Groups groups, String name) {
        for (GroupData group : groups) {
            if (group.getName().equalsIgnoreCase(name) && group.getContacts().size() > 0) {
                return true;
            }
        }
        return false;
    }
}

