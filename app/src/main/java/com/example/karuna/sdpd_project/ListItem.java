package com.example.karuna.sdpd_project;

import android.widget.CheckBox;

public class ListItem {
    String heading;

    boolean morning;
    boolean noon;
boolean night;
    public boolean isMorning() {
        return morning;
    }

    public ListItem(String heading, boolean morning, boolean noon, boolean night) {
        this.heading = heading;
        //this.description = description;
        this.morning = morning;
        this.noon = noon;
        this.night = night;
    }

    public boolean isNoon() {
        return noon;

    }

    public boolean isNight() {
        return night;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }




}