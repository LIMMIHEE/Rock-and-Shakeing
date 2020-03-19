package com.limmihee.rock_and_shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscop {

    public interface Listener{
        void onRotation(float rx, float ry, float rz);
    }
    private Listener listener;
    public void setListener(Listener listenerl){
        listener = listenerl;
    }


    private SensorManager sensormanager ;
    private Sensor sensor ;
    private SensorEventListener sensorEventListener ;

    Gyroscop(Context context){
        sensormanager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensor = sensormanager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(listener != null){
                    listener.onRotation(event.values[0],event.values[1],event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void Register(){
        sensormanager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unRegister(){
        sensormanager.unregisterListener(sensorEventListener);
    }
}
