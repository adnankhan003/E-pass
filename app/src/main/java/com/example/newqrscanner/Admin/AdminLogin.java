package com.example.newqrscanner.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newqrscanner.User.Login;
import com.example.newqrscanner.R;

public class AdminLogin extends AppCompatActivity {
    EditText algmail,alpass;
    Button login,gotouserlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        algmail = findViewById(R.id.adlgmail);
        alpass = findViewById(R.id.adlpass);
        login = findViewById(R.id.adminloginbtn);
        gotouserlogin = findViewById(R.id.gotouserlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gmail = algmail.getText().toString();
                String pass = alpass.getText().toString();
                if (!gmail.isEmpty() && !pass.isEmpty())
                {
                    if (gmail.equals("admin") && pass.equals("123456"))
                    {
                        Intent i = new Intent(AdminLogin.this, MainPage.class);
                        startActivity(i);
                        finish();

                    }else {
                        Toast.makeText(AdminLogin.this, "username and password missmatch", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(AdminLogin.this, "please enter user name password", Toast.LENGTH_SHORT).show();

                }
            }
        });

        gotouserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}