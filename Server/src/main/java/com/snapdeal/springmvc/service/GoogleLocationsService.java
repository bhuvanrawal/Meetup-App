package com.snapdeal.springmvc.service;

import com.snapdeal.springmvc.autocomplete.AutoCompleteResponse;
import com.snapdeal.springmvc.autocomplete.LocationDetails;
import com.snapdeal.springmvc.autocomplete.PredictionElement;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 4/9/15.
 */

@Service
public class GoogleLocationsService {


    private String baseUrl = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=";
    private URL url;
    private HttpURLConnection conn = null;


    public List<LocationDetails> getLocations (String placeSubString) throws Exception {

        try {

            url = new URL(baseUrl+ URLEncoder.encode(placeSubString,"UTF-8")+"&types=establishment&key=AIzaSyA1X6HQ5--0RXGIiXFMfU1Q9fZxoPaftrY");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Accept", "application/json");

        conn.setRequestProperty("input", placeSubString);
        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output;
        String locationsJSON = "";
        System.out.println("Output from Server....\n");
        try {
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                locationsJSON += output;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String a = ";";
        AutoCompleteResponse sugestedPlaces = new AutoCompleteResponse();
        ObjectMapper mapper = new ObjectMapper();
        sugestedPlaces = mapper.readValue(locationsJSON, AutoCompleteResponse.class);

        List<LocationDetails> locations = new ArrayList<LocationDetails>();
        if (sugestedPlaces != null) {
            for (PredictionElement place : sugestedPlaces.getPredictions()) {
                LocationDetails location = new LocationDetails();
                location.setLocationDes(place.getDescription());
                location.setLocationId(place.getId());
                location.setPlace_id(place.getPlace_id());
                locations.add(location);
            }
        }
        return locations;
    }
}
