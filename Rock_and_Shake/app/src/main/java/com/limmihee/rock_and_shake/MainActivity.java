package com.limmihee.rock_and_shake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager manager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
    Sensor M_sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    SensorEventListener M_Event_L ;

    TextView MainV = (TextView)findViewById(R.id.MainText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}
