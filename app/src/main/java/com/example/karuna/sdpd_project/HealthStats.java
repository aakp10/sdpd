package com.example.karuna.sdpd_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HealthStats extends AppCompatActivity {

    MqttHelper mqttHelperPulse;
    MqttHelper mqttHelperSteps;
    TextView dataReceived;
    TextView stepsReceived;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_stats);
        dataReceived = (TextView) findViewById(R.id.dataReceived);
        stepsReceived = (TextView) findViewById(R.id.stepsReceived);
        startMqtt();
    }
    private void startMqtt(){
        mqttHelperPulse = new MqttHelper(getApplicationContext(), "sensor/pulse");
        mqttHelperPulse.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                if(topic.compareTo("sensor/pulse") == 0) {
                    Log.w("Debug from pulse",mqttMessage.toString()+topic);
                    dataReceived.setText(mqttMessage.toString());
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        mqttHelperSteps = new MqttHelper(getApplicationContext(), "sensor/steps");
        mqttHelperSteps.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                if(topic.compareTo("sensor/steps") == 0) {
                    Log.w("Debug from steps",mqttMessage.toString()+topic);
                    stepsReceived.setText(mqttMessage.toString());
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });

    }
}
