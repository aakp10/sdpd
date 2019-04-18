package com.example.karuna.sdpd_project;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
public class HealthStats extends AppCompatActivity {

    String readMessage;
    private static final int REQUEST_ENABLE_BT = 1;
    private OutputStream outputStream;
    private InputStream inStream;
    private Context mContext;
    private TextView mTextView, mTextView2;
    private Thread thread;
    private boolean blueToothStatus = false;
    BluetoothSocket socket;
    // Initialize a new BroadcastReceiver instance
    private BroadcastReceiver mBroadcastReceiverBT = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Bluetooth", "Received data from esp32");
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
                if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                        == BluetoothAdapter.STATE_ON) {

                    blueToothStatus = true;
                    Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
                    BluetoothDevice esp32;
                    if (bondedDevices.size() > 0) {
                        for (BluetoothDevice device : bondedDevices) {
                            if (device.getName().equals("ESP32")) {
                                esp32 = device;
                                try {
                                    Log.v("bluetooth", "Here");
                                    ParcelUuid[] uuids = esp32.getUuids();
                                    socket = esp32.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                                    socket.connect();
                                    outputStream = socket.getOutputStream();
                                    inStream = socket.getInputStream();
                                    mTextView.setText(inStream.read());
                                } catch (Exception e) {
                                    Log.v("Error", e.toString());
                                }
                                break;
                            }
                        }
                    }
                } else {
                    blueToothStatus = false;
                    try {
                        outputStream.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_health_stats);

        // Get the application context
        mContext = getApplicationContext();

        // Initialize a new IntentFilter instance
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


        // Register the broadcast receiver

        mContext.registerReceiver(mBroadcastReceiverBT, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));

        // Get the widgets reference from XML layout
        mTextView = (TextView) findViewById(R.id.dataReceived);
        mTextView2 = (TextView) findViewById(R.id.stepsReceived);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            blueToothStatus = false;
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            blueToothStatus = true;
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            BluetoothDevice esp32;
            if (bondedDevices.size() > 0) {
                for (BluetoothDevice device : bondedDevices) {
                    if (device.getName().equals("ESP32_SDPD")) {
                        esp32 = device;
                        try {
                            ParcelUuid[] uuids = esp32.getUuids();
                            BluetoothSocket socket = esp32.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                            socket.connect();
                            outputStream = socket.getOutputStream();
                            inStream = socket.getInputStream();
                        } catch (Exception e) {
                            Log.v("Error", e.toString());
                        }
                        break;
                    }
                }
            }
        }



        thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(200);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                            }
                        });
                    }

                } catch (InterruptedException e) {
                    Log.v("Error", e.toString());
                }
            }
        };

    }


    Thread receiveThread = new Thread() {
        @Override
        public void run() {
            receiveData();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        receiveThread.start();

    }

    public void receiveData() {

        //byte[] buffer = new byte[256];
        int bytes;
        Log.v("b", "entered");
        // Keep looping to listen for received messages
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        BluetoothDevice esp32;
        if (bondedDevices.size() > 0) {
            for (BluetoothDevice device : bondedDevices) {
                if (device.getName().equals("ESP32")) {
                    esp32 = device;
                    try {
                        Log.v("bluetooth", "Here");
                        ParcelUuid[] uuids = esp32.getUuids();
                        socket = esp32.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                        socket.connect();
                        outputStream = socket.getOutputStream();
                        inStream = socket.getInputStream();


                    } catch (Exception e) {
                        Log.v("Error", e.toString());
                    }
                    break;
                }
            }
        }
        String PULSE = "Pulse";
        boolean flag = false;
        while (true) {
            Log.v("b", "entered");
            if (inStream == null)
                break;
            try {
                byte[] buffer = new byte[256];
                bytes = inStream.read(buffer);            //read bytes from input buffer
                readMessage = new String(buffer, 0, bytes);
                if(readMessage.equals("Pulse"))
                    flag = true;
                if(flag == false) {
                    // Send the obtained bytes to the UI Activity via handler
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            mTextView.setText(readMessage);

                        }
                    });
                }
                else {
                    if (readMessage.equals("Pulse") == false) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                mTextView2.setText(readMessage);

                            }
                        });
                        flag = false;
                    }


                }
                Log.i("logging", readMessage + ""+flag);
            } catch (IOException e) {
                break;
            }

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public static boolean isCharging(Context context) {

        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plug == BatteryManager.BATTERY_PLUGGED_USB || plug == BatteryManager.BATTERY_PLUGGED_AC || plug == BatteryManager.BATTERY_PLUGGED_WIRELESS;
    }
}