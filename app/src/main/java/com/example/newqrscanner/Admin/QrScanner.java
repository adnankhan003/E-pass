package com.example.newqrscanner.Admin;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newqrscanner.Modules.EventAndUserModule;
import com.example.newqrscanner.Modules.QrModule;
import com.example.newqrscanner.R;
import com.example.newqrscanner.User.Login;
import com.example.newqrscanner.User.QrShow;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

// implements onClickListener for the onclick behaviour of button
public class QrScanner extends AppCompatActivity implements View.OnClickListener {

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
    Button scanBtn;
    TextView messageText;

    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        // referencing and initializing
        // the button and textviews
        scanBtn = findViewById(R.id.scanBtn);
        messageText = findViewById(R.id.textContent);
        databaseReference = FirebaseDatabase.getInstance().getReference("QRLIST");
        auth = FirebaseAuth.getInstance();



        // adding listener to the button
        scanBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference = FirebaseDatabase.getInstance().getReference("QRSCANNER");
                databaseReference.child(intentResult.getContents()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userid = auth.getCurrentUser().getUid();
                        EventAndUserModule qrModule = snapshot.getValue(EventAndUserModule.class);
                        if(qrModule != null)
                        {
                            String eventname = qrModule.getEventname();
                            String eventdate = qrModule.getEventdate();
                            String eventtime = qrModule.getEventtime();
                            String username = qrModule.getName();
                            String usernumber = qrModule.getMobileno();
                            String gmail = qrModule.getGmail();
                            String eventid = qrModule.getEventid();
                            String useridd = qrModule.getUserid();


                            Intent intent = new Intent(QrScanner.this, QrView.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("evename",eventname);
                            bundle.putString("eventdate",eventdate);
                            bundle.putString("eventtime",eventtime);
                            bundle.putString("username",username);
                            bundle.putString("usernumber",usernumber);
                            bundle.putString("gmail",gmail);
                            bundle.putString("eventid",eventid);
                            bundle.putString("useridd",useridd);
                           // bundle.putString("noofperson",noofperson);
                            intent.putExtras(bundle);
                            startActivity(intent);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                String id = Login.getUser();

                // if the intentResult is not null we'll set
                // the content and format of scan message
                messageText.setText(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
