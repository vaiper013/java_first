package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().all().size() == 0) {
      app.Group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupDeletion ()  {
    Set<GroupData> before = app.Group().all();
    GroupData deletedGroup = before.iterator().next();
    app.Group().delete(deletedGroup);
    Set<GroupData> after = app.Group().all();
    Assert.assertEquals(after.size(),before.size() -1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }


}
