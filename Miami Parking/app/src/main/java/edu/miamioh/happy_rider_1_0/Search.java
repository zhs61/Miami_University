package edu.miamioh.happy_rider_1_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] parkingLotNameList;
    ArrayList<ParkingLotNames> arraylist = new ArrayList<ParkingLotNames>();
    ParkingLotNames temp;
    ArrayList<ParkingLotNames> sortedList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        parkingLotNameList = new String[MapsActivity.allParkingLots.size()];

        for (int i = 0; i < parkingLotNameList.length; i++) {
            parkingLotNameList[i] = MapsActivity.allParkingLots.get(i).getName();
        }

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < parkingLotNameList.length; i++) {
            ParkingLotNames parkingLotNames = new ParkingLotNames(parkingLotNameList[i]);

            /*
            for christian this is one part of what you are looking for this search method is hard
            to understand but arraylist is what gets displayed at the end of the day. you will need to
            look at the parking lot name and loop throuhg the list of MapsActivity.AllParkingLots to
            compare the two name and if they are similiar then you can look up the type or whatever info
            you need that is how i would do it, not very elegent but i think it will get the job done and
            prevent unwanted search options from showing you will also need to do the same back down below
            i will comment out the spot with a big TODO CHRISTAIN
            tag so that it sticks out you could also at the end just look at the content of the arraylist and
            remove anything that you dont want ill put an example below with the tag TODO EXAMPLE
             */
            if(true){
                // Binds all strings into an array
                arraylist.add(parkingLotNames);
            }

        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Toast.makeText(getApplicationContext(), parkingLotNameList[position], Toast.LENGTH_LONG).show();
                getParkingDetailPage(parkingLotNameList[position]);
            }
        });
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    /**
     * used to calculate the distance between two lots, arpitrary - 5941 becuase lots are too close for normal
     * Euclidan calculations to work so they all come up as some thing like 5941.something so i get
     * rid of the 5941 to make it easier to process and use in calculations
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dist;
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
        }
        return (dist - 5941);
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;

        //default to this to avoid error it is immediately overwritten if we go into try block
        double DesiredLat = 39.5105333;
        double DesiredLng = -84.73087666666667;

        ArrayList<Double> closestNums =new ArrayList<>();// number of things being shown
        ArrayList<String> closestNames = new ArrayList<>(); //names that will be displayed

        //here it looks at the top result and tries to find suggestions based on that
        try {
            //grabs the top listed lot name so it can be used to get other names of lots that are sloe by
            String LotDesired = adapter.getItem(0).getParkingLotName();

            //looks through all of the parking lots and finds the one that they are looking for
            for (int j = 0; j < MapsActivity.allParkingLots.size(); j++) {

                //looks only at top item that is being displayed
                if (LotDesired.equals(MapsActivity.allParkingLots.get(j).getName())) {

                    //assigns it to the desiredLatLng to compare to list of Parking lot objects so we can process it from there
                    DesiredLat = MapsActivity.allParkingLots.get(j).getList().get(0).latitude;
                    DesiredLng = MapsActivity.allParkingLots.get(j).getList().get(0).longitude;
                    break;
                }
            }
        } catch (Exception e){
            //tries to catch a null pointer exception
        }

        //variables to make sure it doesnt search forever and it gives up searching if it takes too long
        int counter = 0;
        int loops = 1;

        //this make look like a duplicate for loop but we need to restart the search now that we have the proper lat and lng to begin our search
        do {
            for (int i=0; i < MapsActivity.allParkingLots.size(); i++){
                double dist = distance(DesiredLat, DesiredLng, MapsActivity.allParkingLots.get(i).getList().get(0).latitude ,MapsActivity.allParkingLots.get(i).getList().get(0).latitude);

                //the meat of the algorithm if you can call it that
                //starts at 0.25 miles then doubles each loop and makes sure only 10 names get put into the list
                //also prevents duplicates from being put into the list
                if (dist <= 0.25*loops && counter < 10 && !(closestNums.contains(dist))){
                    closestNums.add(dist);
                    closestNames.add(MapsActivity.allParkingLots.get(i).getName());
                    counter++;
                }
            }
            loops++;
        } while( counter < 20 && loops <10); //arbitrary limit on the amount of times it can search for new lots

        adapter.filter(text);
        ArrayList<String> filterText = new ArrayList<>();
        for (int i = 0; i < adapter.getCount(); i++) {
            filterText.add(adapter.getItem(i).getParkingLotName());
        }

        final ArrayList<String> finalFilterText = filterText;

        //makes them visible to the app
        for ( int i = 0; i < closestNames.size();i++){
            if (finalFilterText.contains(closestNames.get(i))){
                //do nothing
            } else {
                finalFilterText.add(closestNames.get(i));
                temp = new ParkingLotNames(closestNames.get(i));

                //TODO CHRISTAIN
                if (true){
                    arraylist.add(temp);//this is where it gets put into the list that gets printed out
                }

            }
        }

        //TODO EXAMPLE CHRISTIAN
        //i think this is what you want the most and will make things easier for you
        //for refernce this removes the unwanted options post search while the commented section above
        //removes it presearch so it never gets added i think this is much better but its up to you
        //looking through all parking lots can access their data with
        //MapsActivity.allParkingLots.get(i).getName() or getType();
        for (int i = 0;i < MapsActivity.allParkingLots.size();i++){

            //loop through all the array list taht will spit out the name that will appear on the search list
            for (int j = 0; j < arraylist.size();j++){
                if (true){//your test here CHRISTIAN
                    //do your dirty work here
                    //arraylist.remove(j);
                }
            }

        }

        //adds a on click listener to each of the items in the list that is being displayed
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // Toast.makeText(getApplicationContext(), finalFilterText.get(position), Toast.LENGTH_LONG).show();
                getParkingDetailPage(finalFilterText.get(position));
            }
        });
        return false;
    }

    /**
     * adds the parking lot page once you click the onclick listener that each item has
     * @param parkingLotName
     */
    private void getParkingDetailPage(String parkingLotName) {
        ParkingLot target = null;
        for (int i = 0; i < MapsActivity.allParkingLots.size(); i++) {
            if (MapsActivity.allParkingLots.get(i).getName().equals(parkingLotName)) {
                target = MapsActivity.allParkingLots.get(i);
                break;
            }
        }
        Intent parkingLotDetail = new Intent(Search.this, ParkingLotDetail.class);
        parkingLotDetail.putExtra("lot_name", target.getName());
        parkingLotDetail.putExtra("lot_detail", "Capacity: " + target.getTotal() +"\nAvalibility: " + (target.getTotal() - target.getOccupied()) + "\nType: " + target.getType() );
        startActivity(parkingLotDetail);
    }

}