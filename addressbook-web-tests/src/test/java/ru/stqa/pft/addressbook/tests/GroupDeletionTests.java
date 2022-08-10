package ru.stqa.pft.addressbook.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.Group().all();
    GroupData deletedGroup = before.iterator().next();
    app.Group().delete(deletedGroup);
    assertThat(app.Group().count(), equalTo(before.size() -1));
    Groups after = app.Group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));


  }
}

//before.remove(deletedGroup);
//Assert.assertEquals(before, after);
//assertEquals(after.size(),before.size() -1);
