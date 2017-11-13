package com.example.jc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {

    // Variable Declaration
    TextView tempView3;
    TextView desView3;
    TextView cityView3;
    TextView dateView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Links for Textview to pull data to display
        tempView3 = (TextView) findViewById(R.id.tempView3);
        desView3 = (TextView) findViewById(R.id.desView3);
        cityView3 = (TextView) findViewById(R.id.cityView3);
        dateView3 = (TextView) findViewById(R.id.dateView3);

        dateView3.setText(getCurrentDate()); // Display current date from the phone


        String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=54ffaa1abd28e0f4f0c4dc8736b73ce2&units=metric"; // url to pull weather data

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
                            tempView3.setText(temp);
                            desView3.setText(weatherDescription);
                            cityView3.setText(city);

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
