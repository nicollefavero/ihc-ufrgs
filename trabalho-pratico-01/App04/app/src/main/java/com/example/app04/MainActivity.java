package com.example.app04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    TextView x;
    TextView y;
    TextView z;

    private Sensor mLight;
    TextView lightValue;

    private Sensor mProximity;
    TextView proximityValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getGPSBtn = (Button) findViewById(R.id.getGPSBtn);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        getGPSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if(l!=null)
                {
                    double lat = l.getLatitude();
                    double longi = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: "+lat + "\nLONG: " +
                            longi, Toast.LENGTH_LONG).show();
                }
            }
        });

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        x = (TextView) findViewById(R.id.textViewX);
        y = (TextView) findViewById(R.id.textViewY);
        z = (TextView) findViewById(R.id.textViewZ);
        lightValue = (TextView) findViewById(R.id.textViewLight);
        proximityValue = (TextView) findViewById(R.id.textViewProximity);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mProximity, 2 * 1000 * 1000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];

            x.setText(String.valueOf(sensorX));
            y.setText(String.valueOf(sensorY));
            z.setText(String.valueOf(sensorZ));
        }

        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float sensorLight = event.values[0];
            lightValue.setText(String.valueOf(sensorLight));
        }

        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float sensorProximity = event.values[0];
            proximityValue.setText(String.valueOf(sensorProximity));
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
}