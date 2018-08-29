package com.example.android.railemployeedirectory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity{
    EditText search;
    Button  search_btn;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(EditText) findViewById(R.id.search);
        search_btn=(Button) findViewById(R.id.search_btn);
        Spinner spinner = (Spinner) findViewById(R.id.select_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int
                    position, long id) {


                if (id == 1) {
                    Intent intent = new Intent(MainActivity.this,NameSearchActivity.class);
                    startActivity(intent);}

                else if (id == 2) {
            Intent intent = new Intent(MainActivity.this,RailwayOfficeActivity.class);
            startActivity(intent);}
                /*else if (id == 4) {
                    Intent intent = new Intent(MainActivity.this,DesignationSearchActivity.class);
                    startActivity(intent);}*/

        }


            @Override

            public void onNothingSelected(AdapterView<?> parent) {



            }

        });
    }
    public void insert(View view)
    {
        Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
        startActivity(intent);
    }
    public void delete(View view)
    {
        Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
        startActivity(intent);
    }



}
