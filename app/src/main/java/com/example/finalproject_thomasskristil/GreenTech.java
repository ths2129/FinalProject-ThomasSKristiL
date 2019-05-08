package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GreenTech extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap Tesla, Aecom, TerraCycleIns;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_tech);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Tesla = googleMap;
        Aecom = googleMap;
        TerraCycleIns = googleMap;


        LatLng tesla = new LatLng(40.7410694, -74.0097609);
        Tesla.addMarker(new MarkerOptions().position(tesla).title("Tesla"));
        Tesla.moveCamera(CameraUpdateFactory.newLatLng(tesla));
        Tesla.animateCamera(CameraUpdateFactory.newLatLngZoom(tesla, 10f));


        LatLng aeCom = new LatLng(40.749004, -73.9773403);
        Aecom.addMarker(new MarkerOptions().position(aeCom).title("AECOM"));
        Aecom.moveCamera(CameraUpdateFactory.newLatLng(aeCom));
        Aecom.animateCamera(CameraUpdateFactory.newLatLngZoom(aeCom, 10f));


        LatLng terra = new LatLng(40.2330822, -74.7548232);
        TerraCycleIns.addMarker(new MarkerOptions().position(terra).title("TerraCycle, Inc"));
        TerraCycleIns.moveCamera(CameraUpdateFactory.newLatLng(terra));
        TerraCycleIns.animateCamera(CameraUpdateFactory.newLatLngZoom(terra, 7f));
    }

}
