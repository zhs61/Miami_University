package edu.miamioh.happy_rider_1_0;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.app.AlertDialog;

/**
 * ParkingLotDetail Activity:
 *  Java Object can be used to generate the ParkingLotdeatil page whenever it needs.
 */
public class ParkingLotDetail extends AppCompatActivity {

    /**
     * onCtreate will be called when the activity is called. \
     * read the layout and set the content,
     * the data should be sand in via intent object
     * Ex. parking_lot_name = intentExtra.getStringExtra("lot_name");
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot_detail);

        // Could change the title base on the input string
        final Intent intentExtra = getIntent();
        String lot_name = intentExtra.getStringExtra("lot_name");
        String lot_detail = intentExtra.getStringExtra("lot_detail");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(lot_name);
        setSupportActionBar(toolbar);

        //Set Lot_detail
        TextView lotDetail = (TextView)findViewById(R.id.lot_detail);
        lotDetail.setText(lot_detail);


        // enable the back key
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //here is where we let the user get directions to the destination with google maps
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //grab the name of the parking lot that is currently being displayed
                String locationName = intentExtra.getStringExtra("lot_name");
                Snackbar.make(view, "Getting directions to this location", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                //send that name through the getDirections method that will open up the google maps app with directions to that location
                getDirections(locationName);
            }
        });

    }

    /**
     * this method will get you directions to the parking lot that is currently being displayed in the parking
     * lot details page and only gets called when you click the button to navigate there relies on
     * MapsActivity.allParkingLots and MapsACtivity.allparkinglatlng1 to find what we are looking for
     * @param locationName
     */
    private void getDirections(String locationName) {

        //Log.d("mytag", "looking for: " + locationName );//testing purposes

        //default values are set to where the app opens up first in the middle of miami univeristy these should get overwritten later
        String latitude = "39.5105333";
        String longitude = "-84.73087666666667";

        //loop through all of the parking lot pobjects
        for( int i = 0; i < MapsActivity.allParkingLots.size();i++){

            //print out the names of the parking lot objects as they appear
            //Log.d("mytag", MapsActivity.allParkingLots.get(i).getName());

            //if the parkinglot object is the right one that is currently being displayed do something and stop looping
            if (MapsActivity.allParkingLots.get(i).getName().equals(locationName)){
                latitude = String.valueOf(MapsActivity.allparkinglatlng1.get(i).get(0).latitude);
                longitude = String.valueOf(MapsActivity.allparkinglatlng1.get(i).get(0).longitude);
                break;//stops for loop after you found what you are looking for
            }
        }

        //links our to the google maps app on the android phone if you dont have google maps it
        //finds another map app or lets you choose if you have more than one maps app
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Intent chooser = Intent.createChooser(mapIntent,"Launch Maps");
        startActivity(chooser);
        //Log.d("mytag", locationName );

    }

    /**
     * FloatingActionButton can be changed to some function that we want, right now it is not link
     * to any functions yet.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
