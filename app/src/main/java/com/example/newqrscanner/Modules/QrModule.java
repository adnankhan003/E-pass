package com.example.newqrscanner.Modules;

public class QrModule {
    String tittle;
    String date;
    String time;
    String noofperson;
    String eventid;

    public QrModule(String tittle, String date, String time, String noofperson, String eventid) {
        this.tittle = tittle;
        this.date = date;
        this.time = time;
        this.noofperson = noofperson;
        this.eventid = eventid;
    }

    public QrModule(String name, String date, String time, String noofperson) {
        this.tittle = name;
        this.date = date;
        this.time = time;
        this.noofperson = noofperson;

    }



    public QrModule() {
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoofperson() {
        return noofperson;
    }

    public void setNoofperson(String noofperson) {
        this.noofperson = noofperson;
    }
}
