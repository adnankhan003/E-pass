package com.example.newqrscanner.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.newqrscanner.R;
import com.example.newqrscanner.User.EventList;
import com.example.newqrscanner.User.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity {

    Button qrgeneratorbtn,qrscannerbtn,admineventviewbtn;

    FirebaseAuth auth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.firstmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutmenu:
                Intent intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        auth = FirebaseAuth.getInstance();


        qrgeneratorbtn  = findViewById(R.id.qrgeneratebtn);
        qrscannerbtn  = findViewById(R.id.qrscannerbtn);

        admineventviewbtn = findViewById(R.id.admineventlistbtn);

        qrgeneratorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, QrGenerator.class);
                startActivity(intent);
            }
        });

        qrscannerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, QrScanner.class);
                startActivity(intent);
            }
        });
        admineventviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,AdminEventList.class);
                startActivity(intent);
            }
        });


    }
}