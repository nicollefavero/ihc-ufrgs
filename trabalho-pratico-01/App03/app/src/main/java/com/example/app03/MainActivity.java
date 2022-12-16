package com.example.app03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import java.lang.Math;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView x;
    TextView y;
    TextView z;

    float lastX = 0.0f;
    float lastY = 0.0f;
    float lastZ = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        x = (TextView) findViewById(R.id.textViewX);
        y = (TextView) findViewById(R.id.textViewY);
        z = (TextView) findViewById(R.id.textViewZ);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];

            if ((Math.abs(this.lastX - sensorX) > 10.0f) || (Math.abs(this.lastY - sensorY) > 10.0f) || (Math.abs(this.lastZ - sensorZ) > 10.0f)) {
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra("message", "Posição Correta");
                startActivity(intent);
            } else {
                x.setText(String.valueOf(sensorX));
                y.setText(String.valueOf(sensorY));
                z.setText(String.valueOf(sensorZ));
            }

            this.lastX = sensorX;
            this.lastY = sensorY;
            this.lastZ = sensorZ;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
}