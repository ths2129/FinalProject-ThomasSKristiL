package com.example.finalproject_thomasskristil;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RecycleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mGowanus1, mGowanus2, mBronx, mManhattan1, mManhattan2, mQueens1, mQueens2, mStaten, mJersey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //downloads the mapp
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
        mGowanus1 = googleMap;
        mGowanus2 = googleMap;
        mBronx = googleMap;
        mManhattan1 = googleMap;
        mManhattan2 = googleMap;
        mQueens1 = googleMap;
        mQueens2 = googleMap;
        mStaten = googleMap;
        mJersey = googleMap;




        // Add a marker in Sydney and move the camera
        LatLng gowanus1 = new LatLng(40.6746108,-73.9921886);
        mGowanus1.addMarker(new MarkerOptions().position(gowanus1).title("US Recycling Inc"));
        mGowanus1.moveCamera(CameraUpdateFactory.newLatLng(gowanus1));

        LatLng gowanus2 = new LatLng(40.678336,-73.987445);
        mGowanus2.addMarker(new MarkerOptions().position(gowanus2).title("Lower East Side Ecology Center Electronic Waste Recycling Warehouse"));
        mGowanus2.moveCamera(CameraUpdateFactory.newLatLng(gowanus2));

        LatLng bronx = new LatLng(40.817895,-73.931312);
        mBronx.addMarker(new MarkerOptions().position(bronx).title("New York Recycling"));
        mBronx.moveCamera(CameraUpdateFactory.newLatLng(bronx));
        mBronx.animateCamera(CameraUpdateFactory.newLatLngZoom(bronx,10f));


        LatLng manhattan1 = new LatLng(40.752547,-73.973735);
        mManhattan1.addMarker(new MarkerOptions().position(manhattan1).title("4th Bin"));
        mManhattan1.moveCamera(CameraUpdateFactory.newLatLng(manhattan1));
        mManhattan1.animateCamera(CameraUpdateFactory.newLatLngZoom(manhattan1,10f));



        LatLng manhattan2 = new LatLng(40.750537,-73.980592);
        mManhattan2.addMarker(new MarkerOptions().position(manhattan2).title("ERI"));
       // mManhattan2.moveCamera(CameraUpdateFactory.newLatLng(manhattan2));
        mManhattan2.animateCamera(CameraUpdateFactory.newLatLngZoom(manhattan2,10f));


        LatLng queens1 = new LatLng(40.6939307,-74.0969077);
        mQueens1.addMarker(new MarkerOptions().position(queens1).title("Allocco Recycling"));
        mQueens1.moveCamera(CameraUpdateFactory.newLatLng(queens1));
        mQueens1.animateCamera(CameraUpdateFactory.newLatLngZoom(queens1,10f));


        LatLng queens2 = new LatLng(40.732457,-73.937612);
        mQueens2.addMarker(new MarkerOptions().position(queens2).title("Electronic Straps Recycling"));
        mQueens2.moveCamera(CameraUpdateFactory.newLatLng(queens2));
        mQueens2.animateCamera(CameraUpdateFactory.newLatLngZoom(queens2,10f));



        LatLng statenIsland = new LatLng( 40.635444,-74.128719);
        mStaten.addMarker(new MarkerOptions().position(statenIsland).title("Millennium Recycling Corporation"));
        mStaten.moveCamera(CameraUpdateFactory.newLatLng(statenIsland));
        mStaten.animateCamera(CameraUpdateFactory.newLatLngZoom(statenIsland,10f));



        LatLng jersey = new LatLng(40.739842,-74.044706);
        mJersey.addMarker(new MarkerOptions().position(jersey).title("All American Recycling"));
        mJersey.moveCamera(CameraUpdateFactory.newLatLng(jersey));
        mJersey.animateCamera(CameraUpdateFactory.newLatLngZoom(jersey,10f));




    }
}
