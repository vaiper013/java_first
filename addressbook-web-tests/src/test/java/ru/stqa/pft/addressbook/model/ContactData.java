package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;


@XStreamAlias("contact")



public class ContactData {
    @XStreamOmitField
    private  int id = Integer.MAX_VALUE;
    private String firstName;
    private String lastName;
    private String allPhones;
    private String email;
    private String Address;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String hometwoPhone;
    private File photo;


    public String getHomePhone() { return homePhone; }

    public String getMobilePhone() { return mobilePhone; }

    public String getWorkPhone() { return workPhone; }

    public String getHometwoPhone() { return hometwoPhone; }

    public int getId() { return id; }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() { return Address; }

    public File getPhoto() {   return photo;    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withHometwoPhone(String hometwoPhone) {
        this.hometwoPhone = hometwoPhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withAddress(String Address) {
        this.Address = Address;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


}

//public ContactData(String firstName, String lastName, String allPhones, String allEmail) {
//this.id = Integer.MAX_VALUE;
//this.firstName = firstName;
// this.lastName = lastName;
//this.allPhones = allPhones;
//this.allEmail = allEmail;
//}



//public ContactData(int id, String firstName, String lastName, String allPhones, String allEmail) {
// this.id = id;
//this.firstName = firstName;
// this.lastName = lastName;
//this.allPhones = allPhones;
// this.allEmail = allEmail;
// }

// public ContactData withFaxPhone(String fax) {
//        this.fax = fax;
//        return this;
//    }
//public String getFax() {
//        return fax;
//    }
