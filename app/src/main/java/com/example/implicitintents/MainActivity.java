package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_url;
    private EditText editText_url;

    private Button button_map;
    private EditText editText_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_url = findViewById(R.id.button_url);
        editText_url = findViewById(R.id.editText_url);
        button_url.setOnClickListener(v ->openWebsite(v));

        button_map = findViewById(R.id.button_address);
        editText_map = findViewById(R.id.editText_address);
        button_map.setOnClickListener(v -> openMap(v));
    }

    private void openMap(View view) {
        //grab the data
        // create an intent with the data
        // send it - whoever can start it, will start it
        String address = String.format("geo:0,0?q=" + editText_map.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Log.e("ImplicitIntent", "Cannot handle this intent");
        }
    }

    public void openWebsite(View view){
        //grab the url that you entered
        String url = editText_url.getText().toString();
        //create an intent to tell the system that I have a url that needs to be opened
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        //check to make sure that there is an app or activity that can handle this action
        Log.d("Apps/activities", intent.resolveActivity(getPackageManager()).toString());
        //tells you which app/activites cn handle this intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //if not, you can log the error
        else{
            Log.e("Implicit Intent", "Cannot handle this intent");
        }
        //send the intent


    }
}