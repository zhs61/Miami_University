package edu.miamioh.happy_rider_1_0;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /**
     * below here is where I initialize a bunch of values and variables
     */
    public static GoogleMap mMap;

    static LocationManager locationManager;
    static ArrayList<String> nameArray = new ArrayList<>();
    static ArrayList<String> infoArray = new ArrayList<>();
    static ArrayList<Integer> imageArray = new ArrayList<>();
    static ArrayList<ParkingLot> allParkingLots = new ArrayList<>();
    static ArrayList<ArrayList<LatLng>> allparkinglatlng1 = new ArrayList<>();
    static ArrayList<Polygon> allPolygonLots = new ArrayList<>();


    //her is wehere we pass on context to the various other classes involved in our program
    LocationFinder finder = new LocationFinder(this);
    LocationFinder temp = new LocationFinder(this);
    ParkingLotsActivity parkingLots = new ParkingLotsActivity(this);

    boolean locationOn = false;
    ListView listView;
    int supercount = 0;


    /**
     * main method essentially calls a bunch of other methods and is the first thing that runs when the app
     * opens
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String type = "Red";

        //ParkingLotsActivity newParking = new ParkingLotsActivity(mContext);

        /**
         * here parkingLots object is passing the context onto ParkingLotsActivity page where the methods
         * get_json_data and read_locations_data are being housed they require the context of the app when they are called
         * so an object needs to be created
         */

        parkingLots.get_json_data();
        parkingLots.read_locations_data();

        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.listviewID);
        listView.setAdapter(whatever);

        final Button listToggle = findViewById(R.id.toggleListID);
        listToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleView(listView);
                if (supercount % 2 == 1){
                    PolygonDrawer.updatePolygonsOnMap(allPolygonLots, allParkingLots);
                }
                supercount++;


            }
        });
        final ImageButton locationFinder = findViewById(R.id.LocaitonFinderID);
        locationFinder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.d("mytags", "reached button");
                if (locationOn){
                    finder.marker.remove();

                    Log.d("location", "toggle on");
                    locationOn = false;
                } else {
                    finder = temp;
                    finder.marker = null;
                    finder.getLocation();
                    Log.d("location", "toggle off");
                    locationOn = true;
                }
            }
        });
        final Button parkingLotDetail = findViewById(R.id.ParkingLotDetail);
        parkingLotDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parkingLotDetail = new Intent(MapsActivity.this, ParkingLotDetail.class);
                parkingLotDetail.putExtra("lot_name", "Bachelor Lot");
                parkingLotDetail.putExtra("lot_detail", "Capacity: 100\nAvalibility: 50");
                startActivity(parkingLotDetail);
            }
        });



       final ImageButton search_PL = findViewById(R.id.search_PL);
       search_PL.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent parkingLot_search = new Intent(MapsActivity.this, Search.class);
               startActivity(parkingLot_search);
               //Log.d("mytag", "ladies and gentlemen we found it");

           }
       });



    }

    /**
     * used to toggle the list of known parking lots on teh main screen
     * @param view
     */
    public void toggleView(ListView view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        else if (view.getVisibility() == View.VISIBLE)
            view.setVisibility(View.GONE);
    }

    /**
     * calls the PolygonDrawer functions in a neat method, stop calling this method to stop creating polygonObjects on map
     */
    private void polygonRecursion() {
        List<LatLng> list = null;
        for(int i =0; i < allParkingLots.size();i++){

            //Log.d("mytags", allParkingLots.get(i).getName());
            PolygonDrawer.drawPolygonsOnMap(allParkingLots.get(i).getList(), PolygonDrawer.mapColor(allParkingLots.get(i).getWeight()));

        }
    }

    /**
     *
     * @param lister
     */
     

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
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng miamiU = new LatLng(39.5105333, -84.73087666666667);
        // mMap.addMarker(new MarkerOptions().position(miamiU).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miamiU, 16));

        //these need to be called here
        popAllParkingList(allparkinglatlng1);
        // polygonRecursion();
        // setPolygonClickable();
    }

    public void whenPolyClicked(String name) {
        ParkingLot finalParkingLot = null;
        for (ParkingLot parkingLot : allParkingLots) {
            if (parkingLot.getName().equals(name)) {
                finalParkingLot = parkingLot;
            }
        }
        Intent parkingLotDetail = new Intent(MapsActivity.this, ParkingLotDetail.class);
        parkingLotDetail.putExtra("lot_name", name);
        parkingLotDetail.putExtra("lot_detail", "Capacity: " + finalParkingLot.getTotal() + "\nAvalibility: " + (finalParkingLot.getTotal() - finalParkingLot.getOccupied()) + "\nType: " + finalParkingLot.getType() );
        startActivity(parkingLotDetail);
    }


}


