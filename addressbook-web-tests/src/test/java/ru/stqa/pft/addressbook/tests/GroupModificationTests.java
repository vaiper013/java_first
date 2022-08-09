package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.Group().all().size() == 0) {
            app.Group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.Group().all();
        GroupData modifiedGroup = before.iterator().next();
                GroupData group = new GroupData()
                .withId(modifiedGroup.getId()). withName("test777").withHeader("test4").withFooter("test5");
        app.Group().modify(group);
        Set<GroupData> after = app.Group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);

    }
}

//Comparator<? super GroupData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//before.sort(byid);
//after.sort(byid);
