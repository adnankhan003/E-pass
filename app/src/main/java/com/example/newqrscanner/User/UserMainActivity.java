package com.example.newqrscanner.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.newqrscanner.Admin.MainPage;
import com.example.newqrscanner.Admin.QrGenerator;
import com.example.newqrscanner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMainActivity extends AppCompatActivity {

    Button eventlist,acceptlist;
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
                auth.signOut();
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
        setContentView(R.layout.activity_user_main);

        eventlist =findViewById(R.id.ueventlist);
        acceptlist = findViewById(R.id.uacceptlist);


        eventlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMainActivity.this,EventList.class));
            }
        });

        acceptlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMainActivity.this,UserAcceptList.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        auth = FirebaseAuth.getInstance();
        FirebaseUser checkuserid = auth.getCurrentUser();
        if(checkuserid== null){
            Intent intent  =  new Intent(UserMainActivity.this,Login.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }
}