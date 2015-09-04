package com.example.akashraj.common_place;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import co.geeksters.googleplaceautocomplete.lib.CustomAutoCompleteTextView;


public class CreateEvent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomAutoCompleteTextView customAutoCompleteTextView = (CustomAutoCompleteTextView)findViewById(R.id.atv_places);

        if (customAutoCompleteTextView != null && customAutoCompleteTextView.googlePlace != null) {

            customAutoCompleteTextView.googlePlace.getCountry(); //Return the country name
            customAutoCompleteTextView.googlePlace.getCity(); //Return the city
            customAutoCompleteTextView.googlePlace.getDescription(); //Return the description (city + region + country)
        }

        setContentView(R.layout.createevent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
