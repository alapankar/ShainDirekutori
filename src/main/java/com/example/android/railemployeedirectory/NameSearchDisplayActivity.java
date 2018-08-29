package com.example.android.railemployeedirectory;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.android.railemployeedirectory.NameSearchActivity.EXTRA_MESSAGE;

public class NameSearchDisplayActivity extends AppCompatActivity {

    String message; Button btnRead;
    LinearLayout linearLayout;
    List<CheckBox> allCheckBox;
    int i=1,k=1;
    CheckBox checkBox;
    CheckBox chkbx;
    Button det;
    public static final String EXTRA_MESSAG = "com.example.android.MESSAGE";
    public static final String EXTRA_MESSA = "com.example.android.MESSAGE";
    JSONObject jsonObject,jsonObject1,jsonObject2;
    JSONArray jsonArray;
    String railphn;String firstrailphn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namesearch_display);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        linearLayout = (LinearLayout) findViewById(R.id.layoutlinear);
        chkbx = (CheckBox) findViewById(R.id.checkbox);
        det = new Button(this);
        final Intent intent = getIntent();
        message = intent.getStringExtra(EXTRA_MESSAGE);


        try {

            OkHttpClient client = new OkHttpClient();

            //HttpUrl.Builder urlBuilder = HttpUrl.parse("http://192.168.43.143/crudapi/read.php").newBuilder();
            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://alapankartk.000webhostapp.com/readname.php").newBuilder();
            urlBuilder.addQueryParameter("Name", message);

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
                                try {
                                    String data = response.body().string();

                                    jsonArray = new JSONArray(data);
                                    if(jsonArray.length()==0){
                                        Toast.makeText(getApplicationContext(), "Employee with this name not found",
                                                Toast.LENGTH_LONG).show();
                                    }




                                    else {
                                        jsonObject = jsonArray.getJSONObject(0);
                                        chkbx.setText(jsonObject.getString("Name"));
                                        firstrailphn = jsonObject.getString("Railway_Office");
                                        chkbx.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                det.setOnClickListener(new View.OnClickListener() {
                                                    public void onClick(View v) {
                                                        chkbx = findViewById(R.id.checkbox);
                                                        if (chkbx.isChecked()) {
                                                            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                                                            intent.putExtra(EXTRA_MESSAG, firstrailphn);
                                                            startActivity(intent);
                                                        }

                                                    }
                                                });
                                            }
                                        });
                                        jsonObject1 = jsonArray.getJSONObject(1);
                                        if (jsonObject1.has("Name")) {
                                            checkBox = new CheckBox(NameSearchDisplayActivity.this);
                                            linearLayout.addView(checkBox);
                                            checkBox.setText(jsonObject1.getString("Name"));
                                            railphn = jsonObject1.getString("Railway_Office");
                                            checkBox.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    det.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            Intent intent = new Intent(NameSearchDisplayActivity.this, DetailsActivity.class);
                                                            intent.putExtra(EXTRA_MESSAG, railphn);
                                                            startActivity(intent);
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                        jsonObject2 = jsonArray.getJSONObject(2);
                                        if (jsonObject2.has("Name")) {
                                            checkBox = new CheckBox(NameSearchDisplayActivity.this);
                                            linearLayout.addView(checkBox);
                                            checkBox.setText(jsonObject2.getString("Name"));
                                            railphn = jsonObject2.getString("Railway_Office");
                                            checkBox.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    det.setOnClickListener(new View.OnClickListener() {
                                                        public void onClick(View v) {
                                                            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                                                            intent.putExtra(EXTRA_MESSAG, railphn);
                                                            startActivity(intent);
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                } catch (JSONException e) {

                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            det.setText("Click for more details");
                            linearLayout.addView(det);


                        }
                    });

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
