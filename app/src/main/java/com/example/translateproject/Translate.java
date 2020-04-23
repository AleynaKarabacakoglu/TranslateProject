package com.example.translateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


public class Translate extends AppCompatActivity {
    private static final String TAG = "Translate";
    private EditText etInput;
    private Spinner spLang,spLang2;
    private String[] language ={"az","en","tr"};
    private String[] language2 ={"az","en","tr"};
    public static TextView txtOutput;
    private Button translateBtn;
    private ImageButton oppositeBtn;
    private String word, lang;
    private RequestQueue mqueue;
    private ArrayAdapter<String> dataAdaptorforPlainText;
    private ArrayAdapter<String> dataAdaptorforTranslationText;

    //String yandexKey = "trnsl.1.1.20200420T213659Z.6ffe4c582a0f4540.30079ac61936c66e67b74dde73ce33c6509972ad";

    public void init() {
        etInput = findViewById(R.id.input);
        txtOutput = findViewById(R.id.output);
        translateBtn = findViewById(R.id.btntrans);
        oppositeBtn=findViewById(R.id.btncomp);
        spLang=findViewById(R.id.spinnerLang);
        spLang2=findViewById(R.id.spinnerLang2);
        mqueue = Volley.newRequestQueue(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //Aşağıda Spinnerların içini doldurdum.
        dataAdaptorforPlainText= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,language);
        dataAdaptorforTranslationText=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,language2);

        dataAdaptorforPlainText.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dataAdaptorforTranslationText.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spLang.setAdapter(dataAdaptorforPlainText);
        spLang2.setAdapter(dataAdaptorforTranslationText);

        spLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(Translate.this, "Seçim yapıldı.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }});
        spLang2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(Translate.this, "Seçim yapıldı.",
                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word=etInput.getText().toString();
                //lang="tr-en";
                String dil=spLang.getSelectedItem().toString()+"-"+spLang2.getSelectedItem().toString();

                JsonParse(word,dil);



            }
        });

    }

    private void JsonParse(String msg,String lang) {
        Log.d(TAG, "JsonParse: BU METOD ÇALIŞTI");
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200420T213659Z.6ffe4c582a0f4540.30079ac61936c66e67b74dde73ce33c6509972ad&text="+msg+"&lang="+lang;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        txtOutput.setText(jsonArray.get(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
    mqueue.add(request);
    }


}




