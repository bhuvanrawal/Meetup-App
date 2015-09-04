package com.meetupapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by amit on 4/9/15.
 */
public class Utility {

    public static final String GOOGLE_API_KEY = "AIzaSyA1X6HQ5--0RXGIiXFMfU1Q9fZxoPaftrY";

    public static String getUrl (String baseUrl, HashMap<String, String> params) throws UnsupportedEncodingException {

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            baseUrl += "&"+pair.getKey() + "=" + URLEncoder.encode(pair.getValue().toString(),"UTF-8");
        }
        //String url = URLEncoder.encode(baseUrl, "UTF-8");
        System.out.println(baseUrl);
        return baseUrl;
    }

    public static String getURLResponse (String httpUrl) throws UnsupportedEncodingException {

        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(httpUrl);
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
        String jsonString = "";
        System.out.println("Output from Server....\n");
        try {
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                jsonString += output;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
