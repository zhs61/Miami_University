package edu.miamioh.happy_rider_1_0;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * this class is the backbone to the entire app all classes call from here in one way or another
 * that being said it is a very basic class and easy to understand at the end it has a setPoly method and
 * a onPolygonClick method which do the following
 * setPoly: creates a polygon object associated with each parkinglot object that we can use to display where the
 * parking lots are on the map
 * onPolygonClick: lets us create onClickListeners associated with each parkingLot that links to
 * creating a parkinglotpage(details page) of every parking lot you want to look at.
 */
public class ParkingLot implements GoogleMap.OnPolygonClickListener{

    private final String name;

    //can be: red, yellow, paid
    private final String type;

    private double occupied;

    private final double total;

    private PolygonOptions poly;

    /**
     * weight is calculated after object initalization and is the thing that dictates the color of
     * the polygon shown on screen for each parking lot.
     */
    private double weight;

    private ArrayList<LatLng> list;

    /**
     * use this parking lot creator method to instantiate the object when you already know what the ArrayList of
     * LatLng is going to look like
     * @param name
     * @param type
     * @param occupied
     * @param total
     * @param list
     */
    public ParkingLot(String name, String type, double occupied, double total, ArrayList list){
        this.name = name;
        this.type = type;
        this.occupied = occupied;
        this.total = total;
        this.list = list;
    }

    /**
     * Creates a parkinglot object from the class parkingLotsActivity method get_json_data and reads in the
     * arraylsit<latlng> from the method readLocationsData() located in the same class
     * @param name
     * @param type
     * @param occupied
     * @param total
     */
    public ParkingLot(String name, String type, double occupied, double total){
        this.name = name;
        this.type = type;
        this.occupied = occupied;
        this.total = total;
        this.list = null;
        this.poly = null;
    }

    /**
     * sets weight
     * @param weight
     */
    public void setWeight(double weight){
        this.weight = weight;
    }

    /**
     * sets occupied double
     * @param occ
     */
    public void setOccupied(double occ){
        this.occupied = occ;
    }

    public double getOccupied(){ return this.occupied;}
    public double getTotal(){ return this.total;}
    public String getType() { return this.type;}
    /**
     * returns the weight of the object when the .getWeight() is called we do it on the fly
     * because it changes so often because it changes as a response to Total changing so much
     * @return double weight
     */
    public double getWeight(){
        return this.occupied/this.total;
    }

    /**
     * used o find the name of a specific parking lot used to generate lists and make sure
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * Used to return the list of LatLng that each parkinglot is made of at every step.
     * @return
     */
    public ArrayList<LatLng> getList(){
        return this.list;
    }

    /**
     * set list for each parkingLot object
     * @param list
     */
    public void setList(ArrayList<LatLng> list){
        this.list = list;
    }


    /**
     * make the polygon clickable
     * https://developers.google.com/maps/documentation/android-sdk/polygon-tutorial
     */
    private MapsActivity ma;
    public void setPoly(ArrayList<LatLng> latlng, int c, final MapsActivity m) {
        ma = m;
        Polygon polygon = MapsActivity.mMap.addPolygon(new PolygonOptions()
                .addAll(latlng)
                .strokeWidth(1)
                .strokeColor(c)
                .fillColor(c)
                .clickable(true));
        polygon.setClickable(true);
        polygon.setTag(name);
        ma.mMap.setOnPolygonClickListener(this);
    }

    /**
     * helper method to the setclicklistener, this is called when you
     * @param polygon
     */
    @Override
    public void onPolygonClick(Polygon polygon) {
        ma.whenPolyClicked( (String)polygon.getTag());
    }

    public PolygonOptions getPoly(){
        return this.poly;
    }


}

