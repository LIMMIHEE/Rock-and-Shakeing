package com.limmihee.rock_and_shake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Accelometer accelometer;
    private Gyroscop gyroscop;

    TextView MainV = (TextView)findViewById(R.id.MainText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
