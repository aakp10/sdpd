package com.example.karuna.sdpd_project;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanFilter;
import no.nordicsemi.android.support.v18.scanner.ScanResult;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

public class MainActivity extends AppCompatActivity {

    static MainActivity instance;
    ArrayList<MedicineDetails> medicineList;
    UUID SERVICE_UUID = UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b");
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
        medicineList = new ArrayList<>();
        BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();

    // We want to receive a list of found devices every second
        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setReportDelay(1000)
                .build();

    // We only want to scan for devices advertising our custom service
       /* ScanFilter scanFilter = new ScanFilter.Builder()
                .setServiceUuid(new ParcelUuid(SERVICE_UUID)).build();
        scanner.startScan(Arrays.asList(scanFilter), settings, mScanCallback);*/
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
    public void setMedicineList(ArrayList<MedicineDetails> md) {
        medicineList = md;
    }

    private final ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            // We scan with report delay > 0. This will never be called.
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            if (!results.isEmpty()) {
                ScanResult result = results.get(0);
                BluetoothDevice device = result.getDevice();
                String deviceAddress = device.getAddress();
                // Device detected, we can automatically connect to it and stop the scan
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            // Scan error
        }
    };

    }
