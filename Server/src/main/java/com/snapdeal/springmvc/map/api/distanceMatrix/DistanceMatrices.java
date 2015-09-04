package com.snapdeal.springmvc.map.api.distanceMatrix;

/**
 * Created by amit on 4/9/15.
 */
public class DistanceMatrices {

    private String distance;
    private String time;
    private boolean found;

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
