package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewcontactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        List<ContactData> groups = (List<ContactData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();}
    }

    @Test (dataProvider = "validContacts")
    public void testNewcontactCreation(ContactData contact)  {
        app.goToCon().homeContact();
        Contacts before = app.db().contacts();
        app.goToCon().create(contact);
        assertThat(app.Group().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


    @Test(enabled = false)
    public void testCurrentDir () {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}







//Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//Comparator<? super ContactData> byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byid);
//        after.sort(byid);
//Assert.assertEquals(before, after);
//Assert.assertEquals(after.size(), before.size() + 1);
//before.add(contact);
//contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
//assertThat(after.size(), equalTo(before.size() +1));

//File photo = new File("src/test/resources/stru.png");
//ContactData contact = new ContactData().
//withFirstName("Vasiliy").withLastName("Bochkarev").withAllPhones("7777777").withEmail("vaipermail@rambler.ru").withPhoto(photo);