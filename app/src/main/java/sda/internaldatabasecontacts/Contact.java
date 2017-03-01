package sda.internaldatabasecontacts;

import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.id;

/**
 * Created by RENT on 2017-02-22.
 */

public class Contact implements Parcelable {{

    private int id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    public Contact(int id, String firstName, String surname, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {

        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    //// PARCELABLE
    public Contact(Parcel source) {
        setId(source.readInt());
        setFirstName(source.readString());
        setSurname(source.readString());
        setPhoneNumber(source.readString());
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getFirstName());
        dest.writeString(getSurname());
        dest.writeString(getPhoneNumber());
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }
        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
