package com.example.android.railemployeedirectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RailwayOfficeActivity extends Activity{

    public static final String EXTRA_MESSAGE = "com.example.android.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.railway_search);

    }
    public void retrieve(View view) {
        Intent intent = new Intent(getApplicationContext(), RailwaySearchDisplayActivity.class);
        EditText editText = (EditText) findViewById(R.id.search);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }





}

