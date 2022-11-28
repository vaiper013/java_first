package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group);
            System.out.println(group.getContacts());
        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData").list();
        for (ContactData contact : result) {
            System.out.println(contact);
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public ContactData contactById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData contact = (ContactData) session.createQuery("from ContactData where id=:id")
        .setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;
    }

    public GroupData groupById(int group_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData group = (GroupData) session.createQuery("from GroupData where group_id=:group_id")
        .setParameter("group_id", group_id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return group;
    }


}


