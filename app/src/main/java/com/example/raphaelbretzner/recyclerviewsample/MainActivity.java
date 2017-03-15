package com.example.raphaelbretzner.recyclerviewsample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private RecyclerView recyclerView;
    private List<Evenement> evenements = new ArrayList<>();

    private LocationManager lmanager;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ajouterEvenements();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(evenements));

        String[] cordonnees = getGPS();
        System.out.println("-----------------------------longitude : " + cordonnees[0] + " / latitude :" + cordonnees[1] + "-----------------------------");
    }

    public String[] getGPS() {
        String[] cordoonnees = new String[2];
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
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
            Toast.makeText(getApplicationContext(), "GPS désactivé", Toast.LENGTH_LONG).show();
            System.out.println("----------------------------- GPS DÉSACTIVÉ !!!!!!!! -----------------------------");
        }
        if(location!=null) {
            String longitude = ""+location.getLongitude();
            String latitude = ""+location.getLatitude();
            cordoonnees[0] = longitude;
            cordoonnees[1] = latitude;
        }
        return cordoonnees;
    }

    private void ajouterEvenements() {
        evenements.add(new Evenement("Ma superbe soirée !", "https://www.traditours.com/images/Photos%20Angleterre/ForumLondonBridge.jpg"));
        evenements.add(new Evenement("Soirée sport!", "https://www.traditours.com/images/Photos%20Angleterre/ForumLondonBridge.jpg"));
        evenements.add(new Evenement("Tous chez Adrien !", "http://www.telegraph.co.uk/travel/destination/article130148.ece/ALTERNATES/w620/parisguidetower.jpg"));
        evenements.add(new Evenement("Projet X !", "https://www.franceinter.fr/s3/cruiser-production/2013/08/f12b8c33-0011-11e3-9f7b-782bcb6744eb/640_project-x-503f5649862fd.jpg"));
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
            Toast.makeText(getApplicationContext(), "GPS activé", Toast.LENGTH_LONG).show();
            System.out.println("----------------------------- ACTU GPS ACTIVÉ !!!!!!!! -----------------------------");
        }

    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("----- "  , s+"désactivé");

        if(s.equals("gps")) {
            Toast.makeText(getApplicationContext(), "GPS désactivé", Toast.LENGTH_LONG).show();
            System.out.println("----------------------------- ACTU GPS DÉSACTIVÉ !!!!!!!! -----------------------------");
        }
    }
}