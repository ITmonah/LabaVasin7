package com.example.labavasin7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;
    protected OkHttpClient client = new OkHttpClient();

    private String lat = "55.7522";
    private String lon = "37.6156";
    private String API_key = "982f9add48fc53063a9051b8ff44a0ae";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_fact);
        button1 = (Button) findViewById(R.id.next_fact);
        button2 = (Button) findViewById(R.id.button);
        button3 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lat = "55.7522";
                lon = "37.6156";
                OkHttpHandler handler = new OkHttpHandler();
                handler.execute();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lat = "35.6895";
                lon = "139.692";
                OkHttpHandler handler = new OkHttpHandler();
                handler.execute();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lat = "51.5085";
                lon = "-0.12574";
                OkHttpHandler handler = new OkHttpHandler();
                handler.execute();
            }
        });
    }
    public class OkHttpHandler extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void ... voids) {
            Request.Builder builder = new Request.Builder();

            Request request = builder.url("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&lang=ru&units=metric&appid="+API_key)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject object = new JSONObject(response.body().string());
                return object.getJSONArray("weather").getJSONObject(0).getString("description") + "\n" + object.getJSONObject("main").getString("temp") + " C";
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            textView.setText(o);
        }
    }
}