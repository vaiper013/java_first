package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.Group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupDeletion ()  {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().GroupPage();
    app.Group().delete(deletedGroup);
    assertThat(app.Group().count(), equalTo(before.size() -1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();


  }
}

//before.remove(deletedGroup);
//Assert.assertEquals(before, after);
//assertEquals(after.size(),before.size() -1);
