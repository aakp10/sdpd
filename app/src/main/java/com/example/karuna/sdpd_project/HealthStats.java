package com.example.karuna.sdpd_project;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;

public class HealthStats extends AppCompatActivity {

    private static final UUID SERVICE_UUID = UUID.fromString("4fafc201-1fb5-459e-8fcc-c5c9c331914b");
    private static final UUID PULSE_UUID = UUID.fromString("beb5483e-36e1-4688-b7f5-ea07361b26a8");
    private static final UUID STEPS_UUID = UUID.fromString("a73ea952-debb-4be9-87d0-0ff278a23b1e");
    private BluetoothGatt mConnectedGatt;
    BluetoothAdapter mBluetoothAdapter;

    BluetoothManager bluetoothManager;
    private static final String DEVICE_NAME = "SensorTag";
    private SparseArray<BluetoothDevice> mDevices;
    TextView dataReceived;
    TextView stepsReceived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_stats);
        dataReceived = (TextView) findViewById(R.id.dataReceived);
        stepsReceived = (TextView) findViewById(R.id.stepsReceived);
        dataReceived.setText("81");
        stepsReceived.setText("0");

        bluetoothManager= (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mDevices = new SparseArray<>();
        //mGatt = device.connectGatt(mContext, false, mGattCallback);

    }
    @Override
    protected  void onResume() {
        super.onResume();
        if(mBluetoothAdapter == null  || mBluetoothAdapter.isEnabled())
        {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableIntent);
            finish();
            return;
        }
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "No LE Support", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

    }
    @Override
    protected  void onPause() {
        super.onPause();
        //mBluetoothAdapter.stopLeScan(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu); //todo
        for(int i = 0; i < mDevices.size(); i++) {
            BluetoothDevice device = mDevices.valueAt(i);
            menu.add(0, mDevices.keyAt(i), 0, device.getName());
        }

    }

    @Override
    public boolean onOptionItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_scan:
                    mDevices.clear();
                    startScan();
                        return true;

            default:
                BluetoothDevice device = mDevices.get(item.getItemId());
                Log.i("sdpd bluetooth", "Connecting to "+device.getName());
                mConnectedGatt = device.connectGatt(this, true, mGattCallback);
                //mHandler.sendMessage(Message.obtain()null, MSG_)
                return super.onOptionsItemSelected(item);
        }
    }
    private void startScan()
    {
        BluetoothLeScanner scanner = mBluetoothAdapter.getBluetoothLeScanner();

        ScanCallback mLeScanCallback = new ScanCallback() {

            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
            }
        };
        scanner.startScan(mLeScanCallback);
    }

    private void stopScan() {
        BluetoothLeScanner scanner = mBluetoothAdapter.getBluetoothLeScanner();
        scanner.stopScan(new ScanCallback() {

            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
            }
        });
    }

    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        private int mState = 0;
        private void reset() { mState = 0;}
        private void advance() {mState++;}
        private void enableNextSensor(BluetoothGatt gatt){
            BluetoothGattCharacteristic characteristic;
            switch(mState){
                case 0:
                        Log.d("bluetooth", "Enabling pulse");
                        characteristic = gatt.getService(SERVICE_UUID).getCharacteristic(PULSE_UUID);
                        //characteristic get value;

            }
        }
    };
    private Runnable mStopRunnable = new Runnable() {
        @Override
        public void run() {
            stopScan();
        }
    };
    private Runnable mStartRunnable = new Runnable() {
        @Override
        public void run() {
            startScan();
        }
    };
    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        Log.i("bluetooth", "New LE Device: " + device.getName() + " @ " + rssi);
        /*
         * We are looking for SensorTag devices only, so validate the name
         * that each device reports before adding it to our collection
         */
        if (DEVICE_NAME.equals(device.getName())) {
            mDevices.put(device.hashCode(), device);
            //Update the overflow menu
            invalidateOptionsMenu();
        }
    }
}
