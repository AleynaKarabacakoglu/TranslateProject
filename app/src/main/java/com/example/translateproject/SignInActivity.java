package com.example.translateproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.net.Inet4Address;

public class SignInActivity extends AppCompatActivity {

    private Button btnKyt, btnGiris, btnGirisYap;
    private LinearLayout girisLayout;
    private EditText etMail, etSifre;
    // private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "Giris";
    //FirebaseAuth fAuth;

    public void init() {
        btnKyt = findViewById(R.id.btnKayit);
        btnGiris = findViewById(R.id.btnGiris);
        girisLayout = findViewById(R.id.girisLayout);
        etMail = findViewById(R.id.e_mail);
        etSifre = findViewById(R.id.etSifre);
        btnGirisYap = findViewById(R.id.btnGirisYap);
        // fAuth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();


        btnGiris.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (girisLayout.getVisibility() == View.GONE) {
                                                girisLayout.setVisibility(View.VISIBLE);
                                                btnGiris.setAlpha((float) 0.5);
                                                btnKyt.setVisibility(View.GONE);

                                            } else {
                                                girisLayout.setVisibility(View.GONE);
                                                btnGiris.setAlpha(1);
                                                btnKyt.setVisibility(View.VISIBLE);
                                            }

                                        }
                                    }
        );


        btnGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignInActivity.this,Translate.class);
                startActivity(i);
            }
        });
        btnKyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }}