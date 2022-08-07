package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage();
    List<GroupData> before = app.Group().List();
    GroupData group = new GroupData().withName("test2").withHeader("test3").withFooter("test4");
    app.Group().create(group);
    List<GroupData> after = app.Group().List();
    Assert.assertEquals(after.size(),before.size() +1);

    before.add(group);
    Comparator<? super GroupData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
    before.sort(byid);
    after.sort(byid);
    Assert.assertEquals(before,after);

    }
}

//group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
//int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
//group.setid(after.stream()).max((o1, o2) ->integer.compare(o1.getId(), o2.getId())).get().getId;



