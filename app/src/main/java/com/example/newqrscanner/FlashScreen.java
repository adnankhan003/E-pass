package com.example.newqrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newqrscanner.User.Login;

public class FlashScreen extends AppCompatActivity {
    ImageView flashLogo;
    TextView flashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        flashLogo = findViewById(R.id.flash_logo);
        flashText = findViewById(R.id.flash_text);
        flashLogo.animate().alpha(1).setDuration(2000);
        flashText.animate().alpha(1).setDuration(2000);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(FlashScreen.this,Login.class);
            startActivity(intent);
            finish();
        },2000);

    }
}