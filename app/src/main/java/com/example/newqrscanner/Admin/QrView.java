package com.example.newqrscanner.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.newqrscanner.R;

public class QrView extends AppCompatActivity {
    TextView name,mobileno,gmail;
    Button okbtn;
    String names,phno,gmails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_view);

        name = findViewById(R.id.qrviewname);
        mobileno = findViewById(R.id.qrviewmobileno);
        gmail = findViewById(R.id.qrviewgmail);
        okbtn = findViewById(R.id.qrviewokbtn);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QrView.this,QrScanner.class));
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            names = bundle.getString("username");
            phno = bundle.getString("usernumber");
            gmails = bundle.getString("gmail");

            name.setText(names);
            mobileno.setText(phno);
            gmail.setText(gmails);
        }
    }
}