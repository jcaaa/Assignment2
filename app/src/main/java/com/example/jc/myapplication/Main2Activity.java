package com.example.jc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    // Variable Declaration
    TextView tempView2;
    TextView desView2;
    TextView cityView2;
    TextView dateView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,Main3Activity.class)); // Button to link page 2 to page 3
            }
        });

        // Links for Textview to pull data to display
        tempView2 = (TextView) findViewById(R.id.tempView2);
        desView2 = (TextView) findViewById(R.id.desView2);
        cityView2 = (TextView) findViewById(R.id.cityView2);
        dateView2 = (TextView) findViewById(R.id.dateView2);

        dateView2.setText(getCurrentDate()); // Display current date from the phone


        String url = "http://api.openweathermap.org/data/2.5/weather?id=5128638&appid=54ffaa1abd28e0f4f0c4dc8736b73ce2&units=metric"; // url to pull weather data

        JsonObjectRequest jsObjRequest = new JsonObjectRequest // Json code to request url in the background
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Weather","Response: " + response.toString());

                        // Converts Json string data into double values
                        try {
                            JSONObject mainJSONObject = response.getJSONObject("main"); // gets only main data from the json array
                            JSONArray weatherArray = response.getJSONArray("weather"); // gets weather data from weather in json array
                            JSONObject firstWeatherObject = weatherArray.getJSONObject(0); //starts from index 0

                            String temp = Integer.toString((int) Math.round(mainJSONObject.getDouble("temp"))); // gets temperature data in json array
                            String weatherDescription = firstWeatherObject.getString("description"); // gets weather description data in json aray
                            String city = response.getString("name"); // gets name of city in json array

                            // set textview to the data from json
                            tempView2.setText(temp);
                            desView2.setText(weatherDescription);
                            cityView2.setText(city);

                        } catch (JSONException e) {
                            e.printStackTrace(); // prints data
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);

    }

    // code to get date from phone and displays it format
    private String getCurrentDate (){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, yyyy, MM, dd");
        String formattedDate = dateFormat.format(calendar.getTime());

        return formattedDate;
    }
}

