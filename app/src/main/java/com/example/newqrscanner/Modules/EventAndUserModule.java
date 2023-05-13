package com.example.newqrscanner.Modules;

public class EventAndUserModule {

    String eventname,eventdate,eventtime,eventid;
    String name,mobileno,gmail,userid;

    public EventAndUserModule(String eventname, String eventdate, String eventtime, String eventid, String name, String mobileno, String gmail, String userid) {
        this.eventname = eventname;
        this.eventdate = eventdate;
        this.eventtime = eventtime;
        this.eventid = eventid;
        this.name = name;
        this.mobileno = mobileno;
        this.gmail = gmail;
        this.userid = userid;
    }


    public EventAndUserModule() {
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
