package com.meetupapp.api.distanceMatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 4/9/15.
 */
public class DistanceMatrixRequest {

    private List <String> origins = new ArrayList<>();

    private List <String> destinations = new ArrayList<>();

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }
}
