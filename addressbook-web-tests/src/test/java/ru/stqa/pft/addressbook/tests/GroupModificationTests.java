package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.Group().List().size() == 0) {
            app.Group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.Group().List();
        int index = before.size() - 1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId()). withName("test777").withHeader("test4").withFooter("test5");
        app.Group().modify(index, group);
        List<GroupData> after = app.Group().List();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byid);
        after.sort(byid);
        Assert.assertEquals(before, after);

    }
}
