package com.limmihee.rock_and_shake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

public class Music_shake_play extends AppCompatActivity {
    SensorManager sensorManager ;
    Sensor sensor;
    private  long shakeTime=0;
    private  int shakecount;
    private static  final  float SHAKE_GRAVITY = 2.5f;
    private static  final  float SHAKE_STOP_TIME = 500;

    private Accelometer accelometer;
    private Gyroscop gyroscop;

    float now_shake=3.0f;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_shake_play);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        accelometer = new Accelometer(this);
        gyroscop= new Gyroscop(this);

        mediaPlayer = MediaPlayer.create(this,R.raw.the_spin_wires_girls_like_you);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });


        gyroscop.setListener(new Gyroscop.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if (rz != now_shake  ) {
                    now_shake=rz;
                    mediaPlayer.start();
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                } else{
                    mediaPlayer.pause();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }


}
