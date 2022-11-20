package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.Group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test777").withHeader("test4").withFooter("test5");
        app.goTo().GroupPage();
        app.Group().modify(group);
        assertThat(app.Group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();

    }


}

//Comparator<? super GroupData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//before.sort(byid);
//after.sort(byid);
// before.remove(modifiedGroup);
//        before.add(group);
//        Assert.assertEquals(before, after);
//assertEquals(after.size(), before.size());
