package com.example.newqrscanner.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newqrscanner.Admin.AdminLogin;
import com.example.newqrscanner.Admin.MainPage;
import com.example.newqrscanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText lgmail,lpass;
    Button loginbtn,gotoadmin;
    TextView gotosignup;
    FirebaseAuth auth;

    DatabaseReference databaseReference;
//    public static String gmailtxt;
//    public static String getUser()
//    {
//        return gmailtxt;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lgmail = findViewById(R.id.lgmail);
        lpass = findViewById(R.id.lpass);
        loginbtn = findViewById(R.id.loginbtn);
        gotoadmin = findViewById(R.id.gotoadminbtn);

        gotosignup = findViewById(R.id.gotosignup);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("USERS");

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
        gotoadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, AdminLogin.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String gmailtxt = lgmail.getText().toString();
                String lpasstxt = lpass.getText().toString();
                if (gmailtxt.equals("") && (lpasstxt.equals(""))) {
                    Toast.makeText(Login.this, "please fill all the details", Toast.LENGTH_SHORT).show();

                } else {
                        auth.signInWithEmailAndPassword(gmailtxt,lpasstxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Intent intent = new Intent(Login.this, UserMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                        });
                }
            }
        });
    }
}