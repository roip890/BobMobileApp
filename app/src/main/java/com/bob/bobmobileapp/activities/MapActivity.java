package com.bob.bobmobileapp.activities;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.bob.bobmobileapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by User on 27/12/2017.
 */


public class MapActivity extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mGoogleMap;
    private SupportMapFragment mapFrag;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;

    //location is first boolean
    private boolean setLocationIsFirst;

    //Map Settings
    private boolean isBuildingsEnabled;
    private boolean isIndoorEnabled;
    private boolean isMyLocationEnabled;
    private boolean isTrafficEnabled;

    //Map UI
    private boolean isMapToolbarEnabled;
    private boolean isCompassEnabled;
    private boolean isIndoorLevelPickerEnabled;
    private boolean isMyLocationButtonEnabled;
    private boolean isRotateGesturesEnabled;
    private boolean isScrollGesturesEnabled;
    private boolean isTiltGesturesEnabled;
    private boolean isZoomControlsEnabled;
    private boolean isZoomGesturesEnabled;
    private boolean isAllGesturesEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        this.initMapPreferences();
        this.initUiSetting();
        this.setLocationIsFirst = true;
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                this.configureUiSettings();
                this.configureMapPreferences();
                this.mGoogleMap.setMyLocationEnabled(true);
                //mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            this.configureUiSettings();
            this.configureMapPreferences();
            this.mGoogleMap.setMyLocationEnabled(true);
            //mGoogleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onLocationChanged(Location location)
    {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        if (this.setLocationIsFirst) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
            this.setLocationIsFirst = false;
        }

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private void initMapPreferences() {
        this.setBuildingsEnabled(true);
        this.setIndoorEnabled(true);
        this.setMyLocationEnabled(true);
        this.setTrafficEnabled(true);
    }

    private void configureMapPreferences() {
        this.mGoogleMap.setBuildingsEnabled(true);
        this.mGoogleMap.setIndoorEnabled(true);
        this.mGoogleMap.setTrafficEnabled(true);
    }

    private void initUiSetting() {
        this.setCompassEnabled(true);
        this.setIndoorLevelPickerEnabled(true);
        this.setMapToolbarEnabled(true);
        this.setMyLocationButtonEnabled(true);
        this.setRotateGesturesEnabled(true);
        this.setScrollGesturesEnabled(true);
        this.setTiltGesturesEnabled(true);
        this.setZoomControlsEnabled(true);
        this.setZoomGesturesEnabled(true);
    }

    private void configureUiSettings() {
        this.mGoogleMap.getUiSettings().setCompassEnabled(this.isCompassEnabled);
        this.mGoogleMap.getUiSettings().setIndoorLevelPickerEnabled(this.isIndoorLevelPickerEnabled);
        this.mGoogleMap.getUiSettings().setMapToolbarEnabled(this.isMapToolbarEnabled);
        this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(this.isMyLocationButtonEnabled);
        this.mGoogleMap.getUiSettings().setRotateGesturesEnabled(this.isRotateGesturesEnabled);
        this.mGoogleMap.getUiSettings().setScrollGesturesEnabled(this.isScrollGesturesEnabled);
        this.mGoogleMap.getUiSettings().setTiltGesturesEnabled(this.isTiltGesturesEnabled);
        this.mGoogleMap.getUiSettings().setZoomControlsEnabled(this.isZoomControlsEnabled);
        this.mGoogleMap.getUiSettings().setZoomGesturesEnabled(this.isZoomGesturesEnabled);
    }

    public boolean isMapToolbarEnabled() {
        return this.isMapToolbarEnabled;
    }

    public void setMapToolbarEnabled(boolean mapToolbarEnabled) {
        this.isMapToolbarEnabled = mapToolbarEnabled;
    }

    public boolean isCompassEnabled() {
        return this.isCompassEnabled;
    }

    public void setCompassEnabled(boolean compassEnabled) {
        this.isCompassEnabled = compassEnabled;
    }

    public boolean isIndoorLevelPickerEnabled() {
        return this.isIndoorLevelPickerEnabled;
    }

    public void setIndoorLevelPickerEnabled(boolean indoorLevelPickerEnabled) {
        this.isIndoorLevelPickerEnabled = indoorLevelPickerEnabled;
    }

    public boolean isMyLocationButtonEnabled() {
        return this.isMyLocationButtonEnabled;
    }

    public void setMyLocationButtonEnabled(boolean myLocationButtonEnabled) {
        this.isMyLocationButtonEnabled = myLocationButtonEnabled;
    }

    public boolean isRotateGesturesEnabled() {
        return this.isRotateGesturesEnabled;
    }

    public void setRotateGesturesEnabled(boolean rotateGesturesEnabled) {
        this.isRotateGesturesEnabled = rotateGesturesEnabled;
    }

    public boolean isScrollGesturesEnabled() {
        return this.isScrollGesturesEnabled;
    }

    public void setScrollGesturesEnabled(boolean scrollGesturesEnabled) {
        this.isScrollGesturesEnabled = scrollGesturesEnabled;
    }

    public boolean isTiltGesturesEnabled() {
        return this.isTiltGesturesEnabled;
    }

    public void setTiltGesturesEnabled(boolean tiltGesturesEnabled) {
        this.isTiltGesturesEnabled = tiltGesturesEnabled;
    }

    public boolean isZoomControlsEnabled() {
        return this.isZoomControlsEnabled;
    }

    public void setZoomControlsEnabled(boolean zoomControlsEnabled) {
        this.isZoomControlsEnabled = zoomControlsEnabled;
    }

    public boolean isZoomGesturesEnabled() {
        return this.isZoomGesturesEnabled;
    }

    public void setZoomGesturesEnabled(boolean zoomGesturesEnabled) {
        this.isZoomGesturesEnabled = zoomGesturesEnabled;
    }

    public boolean isAllGesturesEnabled() {
        return this.isAllGesturesEnabled;
    }

    public void setAllGesturesEnabled(boolean allGesturesEnabled) {
        this.isAllGesturesEnabled = allGesturesEnabled;
    }

    public boolean isBuildingsEnabled() {
        return this.isBuildingsEnabled;
    }

    public void setBuildingsEnabled(boolean buildingsEnabled) {
        this.isBuildingsEnabled = buildingsEnabled;
    }

    public boolean isIndoorEnabled() {
        return this.isIndoorEnabled;
    }

    public void setIndoorEnabled(boolean indoorEnabled) {
        this.isIndoorEnabled = indoorEnabled;
    }

    public boolean isMyLocationEnabled() {
        return this.isMyLocationEnabled;
    }

    public void setMyLocationEnabled(boolean myLocationEnabled) {
        this.isMyLocationEnabled = myLocationEnabled;
    }

    public boolean isTrafficEnabled() {
        return this.isTrafficEnabled;
    }

    public void setTrafficEnabled(boolean trafficEnabled) {
        this.isTrafficEnabled = trafficEnabled;
    }
}
