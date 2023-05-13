package com.example.newqrscanner.Modules;

public class RequestModule {


    String Name,gmail,MobileNo,approvedstatus,userid;

    public RequestModule(String name, String gmail, String mobileNo, String approvedstatus, String userid) {
        this.Name = name;
        this.gmail = gmail;
        this.MobileNo = mobileNo;
        this.approvedstatus = approvedstatus;
        this.userid = userid;
    }

    public RequestModule(String name, String gmail, String mobileNo, String approvedstatus) {
        Name = name;
        this.gmail = gmail;
        MobileNo = mobileNo;
       this.approvedstatus = approvedstatus;
    }

    public RequestModule() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getApprovedstatus() {
        return approvedstatus;
    }

    public void setApprovedstatus(String approvedstatus) {
        this.approvedstatus = approvedstatus;
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
//
//    public String getPassword() {
//        return Password;
//    }
//
//    public void setPassword(String password) {
//        Password = password;
//    }




}


