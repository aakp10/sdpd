package com.example.karuna.sdpd_project;

public class ListItem {
    String heading;
    String description;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public ListItem(String heading, String description) {
        this.heading = heading;
        this.description = description;
    }
}