package com.example.android.railemployeedirectory;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.android.railemployeedirectory.NameSearchActivity.EXTRA_MESSAGE;
import static com.example.android.railemployeedirectory.NameSearchDisplayActivity.EXTRA_MESSA;
import static com.example.android.railemployeedirectory.NameSearchDisplayActivity.EXTRA_MESSAG;

public class DetailsActivity extends AppCompatActivity {

    TextView  txtname, txtrailofficephn, txtrailresphn, txtdesig, txtbsnlofficephn,txtbsnlresphn;
    String message;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        txtdesig=(TextView) findViewById(R.id.desigvalueview);
        txtname = (TextView) findViewById(R.id.namevalueview);
        txtrailofficephn = (TextView) findViewById(R.id.railphnvalueview);
        txtrailresphn = (TextView) findViewById(R.id.railresivalueview);
        txtbsnlofficephn = (TextView) findViewById(R.id.BSNLofficephnvalueview);
        txtbsnlresphn=(TextView)findViewById(R.id.BSNLresphnvalueview);

        Intent intent = getIntent();
        message = intent.getStringExtra(EXTRA_MESSAG);


        try {

            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://alapankartk.000webhostapp.com/readrailphn.php").newBuilder();
            urlBuilder.addQueryParameter("Railway_Office", message);


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
                                //txtInfo.setText(response.body().string());

                                try {
                                    String data = response.body().string();

                                    jsonArray = new JSONArray(data);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);

                                    txtdesig.setText(jsonObject.getString("Designation"));

                                    txtname.setText(jsonObject.getString("Name"));

                                    txtrailofficephn.setText(jsonObject.getString("Railway_Office"));

                                    txtrailresphn.setText(jsonObject.getString("Railway_Resi"));

                                    txtbsnlofficephn.setText(jsonObject.getString("BSNL_Office"));
                                    txtbsnlresphn.setText(jsonObject.getString("BSNL_Resi"));



                                } catch (JSONException e) {

                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


            });


        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

}


