package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.Group().all();
        GroupData modifiedGroup = before.iterator().next();
                GroupData group = new GroupData()
                .withId(modifiedGroup.getId()). withName("test777").withHeader("test4").withFooter("test5");
        app.Group().modify(group);
        Groups after = app.Group().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }
}

//Comparator<? super GroupData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//before.sort(byid);
//after.sort(byid);
// before.remove(modifiedGroup);
//        before.add(group);
//        Assert.assertEquals(before, after);
