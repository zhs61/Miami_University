package edu.miamioh.happy_rider_1_0;



import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * this class is only called once and its main job is to populate the AllParkingLot arraylist of objects
 * that is used throughout the entire program. it takes the files: lotpolygons.csv and data.json located in
 * res/raw/lotpolygons.csv and assets/data.json respectively
 */
public class ParkingLotsActivity {

    private Context context;

    /**
     * save the context received via constructor in a local variable
     * @param context
     */
    public ParkingLotsActivity(Context context){
        this.context=context;
    }

    /**
     * reads in from the data.json which is just a simple json file that is used to populate the app
     * when it starts up for now it holds just data (occupied) but everything else there is accurate
     */
    public void get_json_data() {
        String in;

        int position = 0;
        try {
            InputStream is = context.getAssets().open("data.json");
            //Toast.makeText(getApplicationContext(), "now here", Toast.LENGTH_LONG).show();
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();


            in = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(in);
            ParkingLot temp;
            for (int i = 0; i < jsonArray.length(); i++, position++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                /*
                adds the info needed to the parking lots objects that are used throughout the program
                these two line are literally the backbone for this app please dont touch them without
                taking a copy fo the previous version of the code.
                 */
                temp = new ParkingLot(obj.getString("NAME"), obj.getString("TYPE"), obj.getDouble("OCCUPIED"), obj.getDouble("TOTAL"));
                MapsActivity.allParkingLots.add(temp);

                /*
                outdated and discarded functions in out app dont get rid of them though they might
                be useful for later and are good reference on how to call from a json file and read in the data recursively
                 */
                MapsActivity.nameArray.add(obj.getString("NAME"));
                MapsActivity.infoArray.add(obj.getString("OCCUPIED") + " out of " + obj.getString("TOTAL") + " parking spots available");
                MapsActivity.imageArray.add(R.drawable.car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * reads in the lotpolygons.csv and populates Arrayist<latLng> allparkinglatlng1
     * and MapsActivity.allParkingLots with the proper LatLng data so that we can draw the polygons
     * on the map and use them to find distances and locations
     */
    public void read_locations_data() {
        InputStream is = context.getResources().openRawResource(R.raw.lotpolygons);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        int counter = 0;

        try {
            while((line = reader.readLine()) != null){

                ArrayList<LatLng> points = new ArrayList<>();
                //split my comas
                if (counter == 0 ){
                    counter++;
                    continue;
                }
                //read the data and clean the data
                String[] token = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String temp = token[7];
                temp = temp.replace("Polygon ", "");
                temp = temp.replaceAll("[()]","");
                temp = temp.replace("\"", "");
                String[] tempLongLatStr = temp.split(",");

                for(int i = 0; i <= tempLongLatStr.length-1;i++){
                    //tempLongLatStr[i].replaceFirst(" ", "");
                    String[] supertemp = tempLongLatStr[i].split(" ");

                    points.add(
                            new LatLng(
                                    Double.parseDouble(supertemp[1]), Double.parseDouble(supertemp[0])
                            )
                    );
                }

                //adding that data to an array to look at later
                MapsActivity.allparkinglatlng1.add(counter - 1, points);

                counter ++;
            }
        } catch (IOException e){
            Log.wtf("Myactivity", "Error reading datafile on line " + line, e);
            e.printStackTrace();
        }

        /*
         * all parkinglots now have a arraylist of latlng that can be used instead of using
         * mapsactivity.allparkinglatlng1 that one was temp but was used for way to long and never got fixed
         */
        addListtoallObjects(MapsActivity.allparkinglatlng1);
    }

    /**
     * populates the allparkinglots array list with the proper list of latlng that can bes used for various avtivites to call it use
     * MapsActivity.allParkingLots.get(i).getList();
     * @param list
     */
    public void addListtoallObjects(ArrayList<ArrayList<LatLng>> list){
        for (int i = 0; i < MapsActivity.allParkingLots.size();i++){
            MapsActivity.allParkingLots.get(i).setList(list.get(i));
        }
    }




}
