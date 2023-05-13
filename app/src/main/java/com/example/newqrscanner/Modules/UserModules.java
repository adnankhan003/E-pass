package com.example.newqrscanner.Modules;

public class UserModules {

    String Name,gmail,MobileNo,Password,approvedstatus;

    public UserModules(String name, String gmail, String mobileNo, String password) {
        Name = name;
        this.gmail = gmail;
        MobileNo = mobileNo;
        Password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public UserModules() {
    }
}
