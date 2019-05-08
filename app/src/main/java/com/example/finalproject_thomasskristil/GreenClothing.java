package com.example.finalproject_thomasskristil;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GreenClothing extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap pat1, pat2, pat3, pat4, Bennu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_clothing);
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
        pat1 = googleMap;
        pat2 = googleMap;
        pat3 = googleMap;
        pat4 = googleMap;
        Bennu = googleMap;


        LatLng patOne = new LatLng(40.7830456,-73.9772714);
        pat1.addMarker(new MarkerOptions().position(patOne).title("Patagonia"));
        pat1.moveCamera(CameraUpdateFactory.newLatLng(patOne));
        pat1.animateCamera(CameraUpdateFactory.newLatLngZoom(patOne,12f));

        LatLng patTwo = new LatLng(40.7411843,-74.0087436);
        pat2.addMarker(new MarkerOptions().position(patTwo).title("Patagonia"));
        pat2.moveCamera(CameraUpdateFactory.newLatLng(patTwo));
        pat2.animateCamera(CameraUpdateFactory.newLatLngZoom(patTwo,12f));

        LatLng patThree = new LatLng(40.7251244,-73.9940527);
        pat3.addMarker(new MarkerOptions().position(patThree).title("Patagonia"));
        pat3.moveCamera(CameraUpdateFactory.newLatLng(patThree));
        pat3.animateCamera(CameraUpdateFactory.newLatLngZoom(patThree,12f));

        LatLng patFour = new LatLng(40.7232188,-74.0027885);
        pat4.addMarker(new MarkerOptions().position(patFour).title("Patagonia"));
        pat4.moveCamera(CameraUpdateFactory.newLatLng(patFour));
        pat4.animateCamera(CameraUpdateFactory.newLatLngZoom(patFour,12f));

        LatLng bennu = new LatLng(40.7286257,-73.9942363);
        Bennu.addMarker(new MarkerOptions().position(bennu).title("Bennu"));
        Bennu.moveCamera(CameraUpdateFactory.newLatLng(bennu));
        Bennu.animateCamera(CameraUpdateFactory.newLatLngZoom(bennu,12f));

    }
}
