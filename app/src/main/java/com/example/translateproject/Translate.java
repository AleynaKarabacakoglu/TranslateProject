package com.example.translateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Translate extends AppCompatActivity {
    private static final String TAG = "Translate";
    private EditText etInput;
    private Spinner spLang,spLang2;
    private String[] language ={"az","en","tr"}; //burada spinner kullanmayı göstermek amacıyla 3 dil ekledim, bunu çoğaltabiliriz.
    private String[] language2 ={"az","en","tr"};//az-azerice tr-türkçe en - ingilizce çeviri için
    public static TextView txtOutput;
    private Button translateBtn,exitBtn;
    private ImageButton oppositeBtn;
    private String word;
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
        exitBtn=findViewById(R.id.btnExit);

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
        spLang.setSelection(2);


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

                String dil=spLang.getSelectedItem().toString()+"-"+spLang2.getSelectedItem().toString();
                //Spinnerdan aldığım dilleri JsonParse metoduma parametre olarak verdim.
                JsonParse(word,dil);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(Translate.this, SignInActivity.class);
                //Açık olan hesabımızı aşağıdaki metodu kullanarak kapatabiliyoruz.
                FirebaseAuth.getInstance().signOut();
                startActivity(i);
            }

        });

    }

    private void JsonParse(String msg,String lang) {
        Log.d(TAG, "JsonParse: BU METOD ÇALIŞTI");
        //Aşağıdaki url "yandexkey+çevirilecek kelime+ çivirdiğimiz dil-çevirilen dil" den oluşur.
        //url yi tam olarak tarayıcınızda çalıştırırsanız bir Json dizisi döndüğünü görürsünüz.

        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20200420T213659Z.6ffe4c582a0f4540.30079ac61936c66e67b74dde73ce33c6509972ad&text="+msg+"&lang="+lang;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("text");
                    /*json da istediğimiz sonuc "text" dizisinin içinde olduğu için diziyi parse ettim.
                     burada kullandığım kütüphane volley kütüphanesidir, android studio da json kütüphanesinde jsonparser
                     metodu çalışmamaktır, eğer json kütüphanesi kullanacaksanız gson implement etmeniz gerekir.
                     Ben volley kullandığım için, build.gradleda onu implement ettim.*/

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




