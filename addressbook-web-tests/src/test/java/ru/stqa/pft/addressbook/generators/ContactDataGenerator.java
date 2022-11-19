package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {


    @Parameter(names = "-b", description = "Contact count")
    public int count;

    @Parameter(names = "-i", description = "Target file")
    public String file;

    @Parameter(names = "-g", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ru.stqa.pft.addressbook.generators.ContactDataGenerator generator = new ru.stqa.pft.addressbook.generators.ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Urecognized format" + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {

            for (ContactData contact : contacts.toArray(new ContactData[0])) {
                writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),contact.getAddress(), contact.getAllPhones(), contact.getEmail()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Vasiliy %s", i))
                    .withLastName(String.format("Bochkarev %s", i)).withAddress(String.format("Koroleva13 %s", i)).withAllPhones(String.format("7777777 %s", i)).withEmail("vaipermail@rambler.ru"));
        }
        return contacts;
    }
}

//withAddress(String.format("Koroleva13 %s", i))


