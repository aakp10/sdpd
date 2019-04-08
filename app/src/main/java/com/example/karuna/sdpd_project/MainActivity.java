package com.example.karuna.sdpd_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static MainActivity instance;
    ArrayList<MedicineDetails> medicineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Button Activity1 = (Button)findViewById(R.id.A);
        Activity1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity.class);
                startActivity(intent);
            }
        });*/
        instance = this;
    }

    public void HealthStatsClick(View view) {
        Button healthStats = (Button) findViewById(R.id.HealthStats);

        Intent healthIntent = new Intent(this, HealthStats.class);
        //healthIntent.putExtra(ReceiveName.USER_NAME, name);
        startActivity(healthIntent);
    }
    public void MedicineClick(View view) {
        Button meds = (Button) findViewById(R.id.Medicine);

        Intent medsIntent = new Intent(this, Medicine.class);
        //healthIntent.putExtra(ReceiveName.USER_NAME, name);
        startActivity(medsIntent);
    }

    public ArrayList<MedicineDetails> getMedicineList() {
        return medicineList;
    }
}
