package com.example.jc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);             //defined the button
        button.setOnClickListener(new View.OnClickListener(){        //event when button is clicked
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Main2Activity.class));    //calling & defining the intent
                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show(); //display a message when you click the button and enter the page

            }
        });

    }
}
