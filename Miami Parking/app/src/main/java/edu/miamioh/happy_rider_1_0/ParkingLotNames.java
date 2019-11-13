package edu.miamioh.happy_rider_1_0;

public class ParkingLotNames {
    private String parkingLotName;

    private Double distance;

    /**
     * object used in the construction of the search bar, we need the name for finding the lexicon results
     * @param parkingLotName
     */
    public ParkingLotNames(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    /**
     * this is used if you need to initalize the same obj but if you have a distance from the
     * desired lot to the surrounding lots, used to find suggestions based on what you searched for
     * in the search bar based on how close they are to the desired lot at the top of the list of results
     * @param parkingLotName
     * @param Distance
     */
    public ParkingLotNames(String parkingLotName, double Distance) {
        this.parkingLotName = parkingLotName;
        this.distance = Distance;
    }

    public String getParkingLotName() {
        return this.parkingLotName;
    }

    public void setDistance(double dis) { this.distance = dis; }

    public Double getDistance(){return this.distance;}

}