package com.limmihee.rock_and_shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelometer {

    public interface  Listener{
        void onTransration(float tx, float ty, float tz);

    }

    private Listener listener;
    public void setListener(Listener lis){
        listener = lis;
    }

    SensorManager sensormanager ;
    Sensor sensor ;
    SensorEventListener sensorEventListener ;

    Accelometer(Context context){
        sensormanager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensormanager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if( listener != null){

                //if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//                    float ax =event.values[0];
//                    float ay =event.values[1];
//                    float az =event.values[2];
//                    float gravityX = ax / SensorManager.GRAVITY_EARTH;
//                    float gravityY = ay / SensorManager.GRAVITY_EARTH;
//                    float gravityZ = az / SensorManager.GRAVITY_EARTH;
//
//                    float f = gravityX* gravityX + gravityY*gravityY+gravityZ*gravityZ;
//                    double squaredD = Math.sqrt(f.doubleValue());
                    listener.onTransration(event.values[0], event.values[1],event.values[2]);
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
