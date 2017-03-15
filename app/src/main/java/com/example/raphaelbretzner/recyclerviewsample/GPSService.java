package com.example.raphaelbretzner.recyclerviewsample;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by raphaelbretzner on 14/03/2017.
 */


public class GPSService extends Service implements LocationListener {

    private LocationManager lmanager;
    private Location location;
    private MainActivity mainActivity;

    public GPSService(MainActivity activity) {
        this.mainActivity = activity;

        ActivityCompat.requestPermissions(this.mainActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        lmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lmanager.requestLocationUpdates(lmanager.GPS_PROVIDER, 5000, 10, this);

        System.out.println("----------------------------- Vérification GPS -----------------------------");
        //verif disponibilité gps
        if (lmanager.isProviderEnabled(lmanager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                location = null;
            } else {
                lmanager.requestLocationUpdates(lmanager.GPS_PROVIDER, 5000, 10, this);
                location = lmanager.getLastKnownLocation(lmanager.GPS_PROVIDER);
                System.out.println("----------------------------- GPS OK !!!!!!!! -----------------------------");
            }

        } else {
            location=null;
//            Toast.makeText(getApplicationContext(), "GPS désactivé", Toast.LENGTH_LONG).show();
            System.out.println("----------------------------- GPS DÉSACTIVÉ !!!!!!!! -----------------------------");
        }
        if(location!=null) {
            String longitude = ""+location.getLongitude();
            String latitude = ""+location.getLatitude();
            System.out.println("-----------------------------longitude : " + longitude + " / latitude :" + latitude + "-----------------------------");
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d("++++++ ", s+" activé");
        if(s.equals("gps")) {
//            Toast.makeText(getApplicationContext(), "GPS activé", Toast.LENGTH_LONG).show();
            System.out.println("----------------------------- ACTU GPS ACTIVÉ !!!!!!!! -----------------------------");
        }

    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("----- "  , s+"désactivé");

        if(s.equals("gps")) {
//            Toast.makeText(getApplicationContext(), "GPS désactivé", Toast.LENGTH_LONG).show();
            System.out.println("----------------------------- ACTU GPS DÉSACTIVÉ !!!!!!!! -----------------------------");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
