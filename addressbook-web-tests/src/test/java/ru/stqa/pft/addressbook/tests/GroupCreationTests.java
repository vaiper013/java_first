package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage();
    Groups before = app.Group().all();
    GroupData group = new GroupData().withName("test2").withHeader("test3").withFooter("test4");
    app.Group().create(group);
    assertThat(app.Group().count(), equalTo(before.size() +1));
    Groups after = app.Group().all();
        assertThat(after, equalTo
            (before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }
}

//group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
//group.setid(after.stream()).max((o1, o2) ->integer.compare(o1.getId(), o2.getId())).get().getId;
//Comparator<? super GroupData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
//before.sort(byid);
//after.sort(byid);
//Assert.assertEquals(before,after);
//before.add(group);




