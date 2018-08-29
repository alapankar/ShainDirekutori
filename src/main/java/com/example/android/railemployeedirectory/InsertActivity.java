package com.example.android.railemployeedirectory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InsertActivity extends Activity {
    Button btnCreate;
    EditText txtdesig,txtname, txtrailofficephn,txtrailresiphn, txtbsnlofficephn, txtbsnlresiphn, txtmobilephn;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        btnCreate=(Button)findViewById(R.id.insertbtn);
        txtdesig = (EditText) findViewById(R.id.desigview);
        txtname = (EditText) findViewById(R.id.nameview);
        txtrailofficephn = (EditText) findViewById(R.id.railofficephn);
        txtrailresiphn= (EditText) findViewById(R.id.railresiphn);
        txtbsnlofficephn = (EditText) findViewById(R.id.bsnlofficephn);
        txtbsnlresiphn=(EditText) findViewById(R.id.bsnlresiphn);
        txtmobilephn = (EditText) findViewById(R.id.mobilephn);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://alapankartk.000webhostapp.com/create.php").newBuilder();
                    urlBuilder.addQueryParameter("Designation", txtdesig.getText().toString());
                    urlBuilder.addQueryParameter("Name", txtname.getText().toString());
                    urlBuilder.addQueryParameter("Railway_Office", txtrailofficephn.getText().toString());
                    urlBuilder.addQueryParameter("Railway_Resi", txtrailresiphn.getText().toString());
                    urlBuilder.addQueryParameter("BSNL_Office", txtbsnlofficephn.getText().toString());
                    urlBuilder.addQueryParameter("BSNL_Resi", txtbsnlresiphn.getText().toString());
                    urlBuilder.addQueryParameter("Mobile_No", txtmobilephn.getText().toString());




                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {

                                        Toast.makeText(getApplicationContext(), response.body().string(),
                                                Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }

                        ;
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
