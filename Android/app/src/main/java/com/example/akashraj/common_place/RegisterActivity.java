package com.example.akashraj.common_place;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

/*
    EditText etFirstname = (EditText)findViewById(etFirstname);
    EditText etLastname = (EditText)findViewById(etLastname);
    EditText etEmailAddress = (EditText)findViewById(etEmailAddress);
    EditText etPassword = (EditText)findViewById(etPassword);
    EditText etPasswordConfrim = (EditText)findViewById(etPasswordConfrim);

    String ufname = etFirstname.getText().toString();
    String ulname = etLastname.getText().toString();
    String uemail = etEmailAddress.getText().toString();
    String pwd = etPassword.getText().toString();
    String pwdc = etPasswordConfrim.getText().toString();
*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    public  void registerAccount(View v)
    {
     //   Toast.makeText(this, "Registration successful " + ufname, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CreateEvent.class);
        startActivity(intent);
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
