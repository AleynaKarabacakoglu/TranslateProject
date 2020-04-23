package com.example.translateproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText etPassWord, etUserName, etEmail;
    private Button btnNewUser;
    FirebaseAuth fAuth;
    String user, password,email;
    private void init()
    {

        btnNewUser=findViewById(R.id.btnNewUser);
        etEmail=findViewById(R.id.email);
        etPassWord=findViewById(R.id.passWord);
        etUserName=findViewById(R.id.userName);
        fAuth=FirebaseAuth.getInstance();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();

    }
}
