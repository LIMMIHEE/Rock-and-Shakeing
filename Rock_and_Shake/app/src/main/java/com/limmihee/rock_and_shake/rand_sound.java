package com.limmihee.rock_and_shake;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.media.SoundPool;

import java.util.Random;

public class rand_sound extends AppCompatActivity {
    private SoundPool soundPool;
    private Accelometer accelometer;
    private Gyroscop gyroscop;
    private int soundID;
    MediaPlayer mediaPlayer;

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
                if(rz > 3.0f){
                    int Music_n = RandMusic();
                    Play(Music_n);
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }else if(rz < -3.0f){
                    int Music_n = RandMusic();
                    Play(Music_n);
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }
        });
    }

    public void Play(int Music_num){
        switch (Music_num){
            case 1: mediaPlayer = MediaPlayer.create(this,R.raw.e1);break;
            case 2: mediaPlayer = MediaPlayer.create(this,R.raw.e2);break;
            case 3: mediaPlayer = MediaPlayer.create(this,R.raw.e3);break;
            case 4: mediaPlayer = MediaPlayer.create(this,R.raw.e4);break;
            case 5: mediaPlayer = MediaPlayer.create(this,R.raw.e5);break;
            case 6: mediaPlayer = MediaPlayer.create(this,R.raw.e6);break;
            default:mediaPlayer = MediaPlayer.create(this,R.raw.e6);break;
        }

        /*
                switch (Music_num){
            case 1:soundID = soundPool.load(this,R.raw.e1, 1); break;
            case 2: soundID = soundPool.load(this,R.raw.e2, 1);break;
            case 3: soundID = soundPool.load(this,R.raw.e3, 1);break;
            case 4: soundID = soundPool.load(this,R.raw.e4, 1);break;
            case 5: soundID = soundPool.load(this,R.raw.e5, 1);break;
            case 6: soundID = soundPool.load(this,R.raw.e6, 1);break;
            default:soundID = soundPool.load(this,R.raw.e1, 1);break;
        }
        */


        mediaPlayer.start();
    }
    public int RandMusic(){
        Random random= new Random();
        int i = random.nextInt(5)+1;
        return i;
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
