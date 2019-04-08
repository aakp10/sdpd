package com.example.karuna.sdpd_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Medicine extends AppCompatActivity {

    ArrayList<MedicineDetails> medicineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        medicineList = MainActivity.instance.getMedicineList();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_medicine, MedicineDetails);
        ListView listView = (ListView)findViewById(R.id.listview);
        //listView.setAdapter(adapter);
        if(medicineList != null)
        Log.d("medicine", "size"+medicineList.size());
        //CustomAdapter ca = new CustomAdapter();
        //listView.setAdapter(ca);
    }

    class CustomAdapter  extends BaseAdapter{
        @Override
        public int getCount() {
            return medicineList.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            view = getLayoutInflater().inflate(R.layout.medicine_list_view, null);
            TextView medsName = (TextView)view.findViewById(R.id.medsName);
            CheckBox morning = (CheckBox) view.findViewById(R.id.morning);
            CheckBox noon = (CheckBox) view.findViewById(R.id.noon);
            CheckBox night = (CheckBox) view.findViewById(R.id.night);
            return null;
        }
    }
}
