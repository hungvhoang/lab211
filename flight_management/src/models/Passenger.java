package models;

import java.io.Serializable;

public class Passenger implements Serializable {
    private String personalID;
    private String name;
    private String contactNumber;

    public Passenger() {
    }

    public Passenger(String personalID, String name, String contactNumber) {
        this.personalID = personalID;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
