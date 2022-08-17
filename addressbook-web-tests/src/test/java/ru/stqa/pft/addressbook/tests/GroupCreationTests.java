package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List <Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader (new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
      }
    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group)  {
    app.goTo().GroupPage();
    Groups before = app.Group().all();
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
//List <Object[]> list = new ArrayList<Object[]>();
//    list.add(new Object[] {new GroupData().withName("test2").withHeader("test2").withFooter("test2")});
//    list.add(new Object[] {new GroupData().withName("test3").withHeader("test3").withFooter("test3")});
//    list.add(new Object[] {new GroupData().withName("test4").withHeader("test4").withFooter("test4")});



