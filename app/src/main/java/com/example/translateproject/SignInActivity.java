package com.example.translateproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    private Button btnKyt, btnGiris, btnGirisYap;
    private LinearLayout girisLayout;
    private EditText etMail, etSifre;
    private static final String TAG = "Giris";
    FirebaseAuth fAuth;

    public void init() {
        btnKyt = findViewById(R.id.btnKayit);
        btnGiris = findViewById(R.id.btnGiris);
        girisLayout = findViewById(R.id.girisLayout);
        etMail = findViewById(R.id.e_mail);
        etSifre = findViewById(R.id.etSifre);
        btnGirisYap = findViewById(R.id.btnGirisYap);
        fAuth = FirebaseAuth.getInstance();
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
                String email = etMail.getText().toString().trim();
                String password = etSifre.getText().toString().trim();
                if (etMail.getText().toString().trim().equals(""))//girilen edittextler boşsa uyarı verilir.
                    etMail.setError("Mail boş bırakılamaz.");
                else if (etSifre.getText().toString().trim().equals(""))
                    etSifre.setError("Şifre boş bırakılamaz.");
                else {
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "giriş yapıldı", Toast.LENGTH_SHORT).show();
                                checkInfo();
                            } else {
                                Toast.makeText(SignInActivity.this, "giriş yapılamadı" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btnKyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void checkInfo() {
        Log.d(TAG, "onAuthStateChanged: currentuser!=null");

                    Intent i = new Intent(SignInActivity.this, Translate.class);
                    finish();
                    startActivity(i);
    }

}
