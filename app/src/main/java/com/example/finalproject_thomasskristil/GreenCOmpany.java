package com.example.finalproject_thomasskristil;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GreenCOmpany extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap Tesla, Aecom, Lush1, Lush2, Lush3, Lush4, Lush5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_company);
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
        Lush1 = googleMap;
        Lush2 = googleMap;
        Lush3 = googleMap;
        Lush4 = googleMap;
        Lush5 = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng lush1 = new LatLng(40.778511, -73.953737);
        Lush1.addMarker(new MarkerOptions().position(lush1).title("Lush Cosmetics"));
        Lush1.moveCamera(CameraUpdateFactory.newLatLng(lush1));
        Lush1.animateCamera(CameraUpdateFactory.newLatLngZoom(lush1, 10f));

        LatLng lush2 = new LatLng(40.7824787, -73.9833172);
        Lush2.addMarker(new MarkerOptions().position(lush2).title("Lush Cosmetics"));
        Lush2.moveCamera(CameraUpdateFactory.newLatLng(lush2));
        Lush2.animateCamera(CameraUpdateFactory.newLatLngZoom(lush2, 10f));

        LatLng lush3 = new LatLng(40.7669606, -73.9851687);
        Lush3.addMarker(new MarkerOptions().position(lush3).title("Lush Cosmetics"));
        Lush3.moveCamera(CameraUpdateFactory.newLatLng(lush1));
        Lush3.animateCamera(CameraUpdateFactory.newLatLngZoom(lush3, 10f));

        LatLng lush4 = new LatLng(40.7634887, -73.9691865);
        Lush4.addMarker(new MarkerOptions().position(lush4).title("Lush Cosmetics"));
        Lush4.moveCamera(CameraUpdateFactory.newLatLng(lush1));
        Lush4.animateCamera(CameraUpdateFactory.newLatLngZoom(lush4, 12f));

        LatLng lush5 = new LatLng(40.7352255, -73.9945883);
        Lush5.addMarker(new MarkerOptions().position(lush5).title("Lush Cosmetics"));
        Lush5.moveCamera(CameraUpdateFactory.newLatLng(lush5));
        Lush5.animateCamera(CameraUpdateFactory.newLatLngZoom(lush5, 12f));
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.game_menu, menu);
            return true;



    }
}
