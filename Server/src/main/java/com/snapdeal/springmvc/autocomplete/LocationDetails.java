package com.snapdeal.springmvc.autocomplete;

/**
 * Created by amit on 4/9/15.
 */
public class LocationDetails {

    private String locationDes;
    private String locationId;
    private String place_id;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLocationDes() {
        return locationDes;
    }

    public void setLocationDes(String locationDes) {
        this.locationDes = locationDes;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
