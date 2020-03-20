package com.limmihee.rock_and_shake;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class rand_sound extends AppCompatActivity {
    private Accelometer accelometer;
    private Gyroscop gyroscop;

    SensorManager sensormanager ;
    Sensor sensor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rand_sound);

        sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelometer = new Accelometer(this);
        gyroscop= new Gyroscop(this);

        accelometer.setListener(new Accelometer.Listener() {
            @Override
            public void onTransration(float tx, float ty, float tz) {
                //    if(tz > 1.0f){
                //        getWindow().getDecorView().setBackgroundColor(Color.RED);
                //    }else if(tz < -1.0f){
                //        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                //    }
            }
        });

        gyroscop.setListener(new Gyroscop.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if(rz > 1.0f){

                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }else if(rz < -1.0f){

                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelometer.Register();
        gyroscop.Register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelometer.unRegister();
        gyroscop.unRegister();
    }
}
