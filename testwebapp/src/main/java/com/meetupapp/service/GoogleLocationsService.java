package com.meetupapp.service;

import com.meetupapp.api.autocomplete.AutoCompleteResponse;
import com.meetupapp.api.autocomplete.LocationDetails;
import com.meetupapp.api.autocomplete.PredictionElement;
import com.meetupapp.api.distanceMatrix.DistanceMatrices;
import com.meetupapp.api.distanceMatrix.DistanceMatrixResponse;
import com.meetupapp.api.distanceMatrix.ElementElement;
import com.meetupapp.api.distanceMatrix.RowElement;
import com.meetupapp.utils.Utility;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amit on 4/9/15.
 */

@Service
public class GoogleLocationsService {


    private static final String AUTO_COMPLETE_BASE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
    private static final String DISTANCE_MATRIX_BASE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";


    public List<LocationDetails> getLocations (String placeSubString) throws Exception {


        HashMap<String, String> params = new HashMap<>();
        params.put("input", placeSubString);
        params.put("types", "establishment");
        params.put("key", Utility.GOOGLE_API_KEY);

        //Get url
        String url = Utility.getUrl(AUTO_COMPLETE_BASE_URL, params);

        System.out.println("Http url : " + url);

        //Get Response
        String locationsJSON = Utility.getURLResponse(url);

        //Get Object from json
        AutoCompleteResponse suggestedPlaces = new AutoCompleteResponse();
        ObjectMapper mapper = new ObjectMapper();
        suggestedPlaces = mapper.readValue(locationsJSON, AutoCompleteResponse.class);

        List<LocationDetails> locations = new ArrayList<LocationDetails>();
        if (suggestedPlaces != null) {
            for (PredictionElement place : suggestedPlaces.getPredictions()) {
                LocationDetails location = new LocationDetails();
                location.setLocationDes(place.getDescription());
                location.setLocationId(place.getId());
                location.setPlace_id(place.getPlace_id());
                locations.add(location);
            }
        }
        return locations;
    }


    public List<DistanceMatrices> getDistanceMatrices (List<String> sources, List<String> dests) throws Exception {

        String origins="";
        String destinations = "";
        for (int i=0; i<sources.size() ; i++) {
            origins += sources.get(i) + "|";
            destinations += dests.get(i) + "|";
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("origins", origins);
        params.put("destinations", destinations);
        params.put("key", Utility.GOOGLE_API_KEY);

        //Get url
        String url = Utility.getUrl(DISTANCE_MATRIX_BASE_URL, params);

        //Get Response
        String distanceJSON = Utility.getURLResponse(url);

        //Get Object from json
        DistanceMatrixResponse distanceMatrixResponse = new DistanceMatrixResponse();
        ObjectMapper mapper = new ObjectMapper();
        distanceMatrixResponse = mapper.readValue(distanceJSON, DistanceMatrixResponse.class);

        List<DistanceMatrices> distanceMatrices = new ArrayList<>();
        int i=0;
        for (RowElement rowElement : distanceMatrixResponse.getRows()) {
            DistanceMatrices dis = new DistanceMatrices();
            dis.setFound(false);
            ElementElement element = (rowElement.getElements())[i++];
            if (element.getStatus().equals("OK")) {
                dis.setDistance(element.getDistance().getText());
                dis.setTime(element.getDuration().getText());
                dis.setFound(true);
            }
            distanceMatrices.add(dis);
        }
        return distanceMatrices;
    }


}
