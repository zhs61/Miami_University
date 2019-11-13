package edu.miamioh.happy_rider_1_0;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;



public class LocationFinder extends Activity {
    // variable to hold context
    private Context context;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    /**
     * save the context received via constructor in a local variable
     * https://stackoverflow.com/questions/40142331/how-to-request-location-permission-at-runtime
     * https://stackoverflow.com/questions/44370162/get-location-permissions-from-user-in-android-application
     * @param context
     */
    public LocationFinder(Context context){
        this.context=context;
    }

    /**
     * this is where the get location method should be called but i dont understand context so i cant do it
     */

    private static final int REQUEST_LOCATION_PERMISSION = 0;
    Marker marker;
    LocationListener locationListener;

    public void getLocation() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(context.getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality() + ":";
                    result += addresses.get(0).getCountryName();
                    LatLng latLng = new LatLng(latitude, longitude);

                    if (marker != null) {
                        //Log.d("mytags", "this is lat: " + latitude + ", this is lng: " + longitude);
                    } else {
                        marker = MapsActivity.mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        MapsActivity.mMap.setMaxZoomPreference(20);
                        MapsActivity.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));

                        //Log.d("mytags", "dinosuar this is lat: " + latitude + "this is lng: " + longitude);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        while (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
        MapsActivity.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        MapsActivity.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

}
