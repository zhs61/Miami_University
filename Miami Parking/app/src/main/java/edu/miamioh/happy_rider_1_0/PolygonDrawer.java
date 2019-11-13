package edu.miamioh.happy_rider_1_0;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Random;

import static edu.miamioh.happy_rider_1_0.MapsActivity.allParkingLots;

public class PolygonDrawer {

    /**
     * array of color objects that store a range of colors that is displayed on the map
     * green is lighter intensity and red is highest intensity, purple is a dev color for us to see problems with data
     */
    static int[] colors = {
            Color.rgb(225,245,254),
            Color.rgb(179,229,252),
            Color.rgb(129,212,250),
            Color.rgb(79,195,247),
            Color.rgb(41,182,246),
            Color.rgb(3,169,244),
            Color.rgb(3,155,229),
            Color.rgb(2,136,209),
            Color.rgb(2,119,189),
            Color.rgb(1,87,155),
            Color.rgb(128, 0, 128)// purple dev color
    };


    /**
     * takes in a weight from a parking lot object and give you back a color from the colors[] array above
     * @param weight
     * @return
     */
    public static int mapColor(double weight) {
        if (weight <= 1.00 && weight >= 0.00) {
            if (weight <= 0.10) //green
                return colors[0];
            else if (weight <= 0.20) //yellow
                return colors[1];
            else if (weight <= 0.30) //yellow
                return colors[2];
            else if (weight <= 0.40) //yellow
                return colors[3];
            else if (weight <= 0.50) //yellow
                return colors[4];
            else if (weight <= 0.60) //yellow
                return colors[5];
            else if (weight <= 0.70) //yellow
                return colors[6];
            else if (weight <= 0.80) //yellow
                return colors[7];
            else if (weight <= 0.90) //yellow
                return colors[8];
            else
                return colors[9];
        }
        else
            return colors[10];
    }

    /**
     *
     * @param list
     * @param c
     */
    public static void drawPolygonsOnMap(ArrayList<LatLng> list, int c){
        Polygon outline;

        /*
        for christian here is where they are initially drawn on the map i dont think you want this
        look below for what you want
         */

        if (true){
            for (int i =0; i < list.size();i++){
                outline = MapsActivity.mMap.addPolygon(new PolygonOptions()
                        .addAll(list)
                        .strokeWidth(1)
                        .strokeColor(c)
                        .fillColor(c)
                        .clickable(true)
                );
        }
            //MapsActivity.allPolygonLots.add(outline);
            //MapsActivity.allParkingLots.get(i).setPoly(outline);

        }
    }

    /**
     *
     * @param poly
     * @param lots
     */
    public static void updatePolygonsOnMap(ArrayList<Polygon> poly, ArrayList<ParkingLot> lots){
        Random r;
        double randomValue;
        int c;

        /*
        for christian here is where they get updated after they get drawn this is what you want i think
        for now it just changes the color that is all it does you might have to set the visibility
        on the ones that you want use this .setVisible(boolean) to change weather they are visible
        or not based on the parking lot type i do not recommend deleting the lot you might also have to
        setClickable(false) also another thing is that the array used is the MapsActivity.AllPolygonLots
        not the MapsActivity.AllParkingLots like you might think but you can still use the tests for the
        MapsActivity.AllParkingLots jut fine because they are lined up i dont recommend changing this you
        can though if you want right now all parking lots have and array of LatLngs that can be used to draw
        the polygons but the List of polygons (MapsActivity.AllPolygonLots) has click listeners attached to them
        they two line up perfectly so no need to mess with it to hard, if you have any questions let me know,
        right now the updatePolygonsOnMap is is called in MapsActivity in a button but we should change that to when
        ever your method that you wrote for querying the database happens is called.
         */
        if (true){//this might not be the right if test look in the for loop for the right one
            //loops through all the parking lots
            for (int i =0; i < poly.size();i++){

                //make sure not to get rid of any developer color
                if (poly.get(i).getFillColor() == colors[10]){// if the parking lot is purple then leave it that way else randomize
                    continue;
                }

                r = new Random();//
                randomValue = 0 + (1 - 0) * r.nextDouble();
                c = mapColor(randomValue);

                if (true){//here is where the stuff happens and the color get reassigned
                    poly.get(i).setStrokeColor(c);
                    poly.get(i).setFillColor(c);
                }

            }
        }

    }//end updatePolygonsOnMap

    public void rePopAllParkingList(ArrayList<ArrayList<LatLng>> lister, String type){
        MapsActivity.mMap.clear();
        for(int i = 0; i< allParkingLots.size();i++){
            if (allParkingLots.get(i).getType().equals(type)) {
                allParkingLots.get(i).setPoly(lister.get(i),PolygonDrawer.mapColor(allParkingLots.get(i).getWeight()), MapsActivity);
            }
        }

    }
}
