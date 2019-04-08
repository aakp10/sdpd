package com.example.karuna.sdpd_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {

    TextView medsName;
    CheckBox morning;
    CheckBox noon;
    CheckBox night;
    TextView qty;
    ArrayList<MedicineDetails> medicineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        medsName = findViewById(R.id.medsName);
        morning = findViewById(R.id.morning);
        noon = findViewById(R.id.noon);
        night = findViewById(R.id.night);
        qty = findViewById(R.id.qty);
        medicineList = MainActivity.instance.getMedicineList();
    }
    public void saveMeds(View view) {
        MedicineDetails newMeds = new MedicineDetails(medsName.getText().toString(),
                                                  morning.isChecked(),
                                                  noon.isChecked(),
                                                  night.isChecked(),
                Integer.parseInt(qty.getText().toString()));
        medicineList.add(newMeds);
    }


}
