package models;

public class Staff {
    private String staffID;
    private String name;
    private String password;

    public Staff(String staffID, String name, String password) {
        this.staffID = staffID;
        this.name = name;
        this.password = password;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
