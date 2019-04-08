package com.example.karuna.sdpd_project;

public class MedicineDetails {
    public String name;
    public boolean isMorning;
    public boolean isNoon;
    public boolean isNight;
    public int qty;

    MedicineDetails(String name, boolean isMorning, boolean isNoon, boolean isNight, int qty){
        this.name = name;
        this.isMorning = isMorning;
        this.isNoon = isNoon;
        this.isNight = isNight;
        this.qty = qty;
    }
}
