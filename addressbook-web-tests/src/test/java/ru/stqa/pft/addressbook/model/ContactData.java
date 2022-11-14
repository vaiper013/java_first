package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.File;
import java.util.Objects;


@XStreamAlias("contact")

@Entity
@Table (name = "addressbook")

public class ContactData {
@Id
@Column (name = "id")



    @XStreamOmitField
    private  int id = Integer.MAX_VALUE;
    @Column (name = "firstName")
    private String firstName;
    @Column (name = "lastName")
    private String lastName;

    @Transient
    private String group;
    @Transient
    private String allPhones;
    private String email;
    private String emailtwo;
    private String emailthree;
    private String Address;
    @Column (name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column (name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column (name = "work")
    @Type(type = "text")
    private String workPhone;
    private String hometwoPhone;
    @Column (name = "photo")
    @Type(type = "text")
    private String photo;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

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

    public String getEmailtwo() { return emailtwo; }

    public String getEmailthree() { return emailthree; }

    public String getAddress() { return Address; }

    public File getPhoto() {return new File(photo);}

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
    public ContactData withEmailtwo(String emailtwo) {
        this.emailtwo = emailtwo;
        return this;
    }

    public ContactData withEmailthree(String emailthree) {
        this.emailthree = emailthree;
        return this;
    }

    public ContactData withAddress(String Address) {
        this.Address = Address;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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
