package com.example.karuna.sdpd_project;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;

public class HealthStats extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;

    BluetoothManager bluetoothManager;


    TextView dataReceived;
    TextView stepsReceived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_health_stats);
        dataReceived = (TextView) findViewById(R.id.dataReceived);
        stepsReceived = (TextView) findViewById(R.id.stepsReceived);
        dataReceived.setText("81");
        stepsReceived.setText("0");
        Button button = (Button) findViewById(R.id.temp);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Integer steps = Integer.parseInt(stepsReceived.getText().toString());
            steps+=1;
                stepsReceived.setText(steps.toString());
            }
        });*/
       /* BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        BluetoothAdapter mBluetoothAdapter = mBluetoothManager.getAdapter();
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(deviceAddress);
        mGatt = device.connectGatt(mContext, false, mGattCallback);*/

    }

}
