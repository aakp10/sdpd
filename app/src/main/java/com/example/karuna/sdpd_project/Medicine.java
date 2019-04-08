package com.example.karuna.sdpd_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Medicine extends AppCompatActivity {

    ArrayList<MedicineDetails> medicineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        medicineList = MainActivity.instance.getMedicineList();
    }
    private void updateMeds()
    {
        for(MedicineDetails m: medicineList) {

        }
    }
}
