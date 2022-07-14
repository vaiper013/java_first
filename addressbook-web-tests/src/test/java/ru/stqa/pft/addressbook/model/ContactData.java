package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String allPhones;
    private final String allEmail;

    public ContactData(String firstName, String lastName, String allPhones, String allEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.allPhones = allPhones;
        this.allEmail = allEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmail() {
        return allEmail;
    }
}
