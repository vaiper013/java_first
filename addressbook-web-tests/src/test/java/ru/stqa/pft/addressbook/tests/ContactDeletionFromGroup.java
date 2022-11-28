package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactDeletionFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goToCon().homeContact();
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.Group().create(new GroupData().withName("test2"));
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0 || selectGroupDel(groups, null) == null) {
            app.goToCon().create(new ContactData()
                    .withFirstName("Vasiliy")
                    .withLastName("Bochkarev")
                    .withAddress("Koroleva13")
                    .withAllPhones("7777777")
                    .withEmail("vaipermail@rambler.ru").inGroup(app.db().groups().iterator().next()));
        }

    }

    @Test
    public void testContactDeletionFromGroup() {
        Groups beforeGroups = app.db().groups();
        ContactData contact = app.db().contacts().iterator().next();
        GroupData groupDeletion = null;
        groupDeletion = selectGroupDel(beforeGroups, groupDeletion);
        app.goToCon().delContactFromGroup(contact, groupDeletion);
        app.goToCon().homeContact();
        ContactData contactWithOutGroup = app.db().contactById(contact.getId());
        GroupData afterGroup = app.db().groupById(groupDeletion.getId());
        Assert.assertFalse(contactWithOutGroup.getGroups().contains(afterGroup));
        Assert.assertFalse(afterGroup.getContacts().contains(contactWithOutGroup));
    }

    private GroupData selectGroupDel(Groups beforeGroups, GroupData groupDeletion) {
        for (GroupData group : beforeGroups) {
            if (group.getContacts().size() != 0) {
                groupDeletion = group;
                break;
            }
        }
        return groupDeletion;
    }
}

