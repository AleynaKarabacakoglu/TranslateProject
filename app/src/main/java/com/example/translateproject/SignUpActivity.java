package com.example.translateproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        if(fAuth.getCurrentUser()!=null)
        {
            Intent i = new Intent(SignUpActivity.this, Translate.class);
            startActivity(i);
            finish();
        }
        btnNewUser.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                user= etUserName.getText().toString().trim();
                email= etEmail.getText().toString().trim();
                password= etPassWord.getText().toString().trim();

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "kullanıcı oluşturuldu",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUpActivity.this, Translate.class);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, "kullanıcı oluşturulamadı"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }}

