package com.example.newqrscanner.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newqrscanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class QrShow extends AppCompatActivity {

    TextView rname,rdate,rtime;
    DatabaseReference reference;
    ImageView qrcodeimg;
    FirebaseAuth auth;
    FirebaseUser fuser;
    Button rconfirmbtn;
    String eventid,useridd;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_result);



        fuser = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();

        rname = findViewById(R.id.rname);
        rdate = findViewById(R.id.rdate);
        rtime = findViewById(R.id.rtime);
        qrcodeimg = findViewById(R.id.rqrimage);

        rconfirmbtn = findViewById(R.id.rconfirmbtn);

        Bundle bundle =getIntent().getExtras();
        if (bundle!=null)
        {
            String name = bundle.getString("evename");
            String date = bundle.getString("eventdate");
            String time = bundle.getString("eventtime");
            eventid = bundle.getString("eventid");
            useridd = bundle.getString("userid");
           // String noofperson = bundle.getString("noofperson");
            rname.setText(name);
            rdate.setText(date);
            rtime.setText(time);
           // rnoofper.setText(noofperson);
    }
        reference = FirebaseDatabase.getInstance().getReference("ACCEPT LIST");

        String merge = useridd+eventid;
//        storageReference = FirebaseStorage.getInstance().getReference().child("QR CODE/h5DoblzLGugqzMmHNbuI59dCkC92/-NV8Uq8p5rUgwzA_TZ8o");
        storageReference = FirebaseStorage.getInstance().getReference().child("QR CODE/"+merge);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String down = uri.toString();
               // Toast.makeText(QrShow.this, down, Toast.LENGTH_SHORT).show();
                Picasso.with(QrShow.this).load(down).into(qrcodeimg);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(QrShow.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        Glide.with(QrShow.this)
//                .load(storageReference)
//                .into(qrcodeimg);



//       storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//          @Override
//          public void onComplete(@NonNull Task<Uri> task) {
//              if (task.isSuccessful()){
//                  String filepath = storageReference.getDownloadUrl().toString();
//                  Toast.makeText(QrShow.this, filepath, Toast.LENGTH_SHORT).show();
////        Picasso.with(QrShow.this).load(filepath).into(qrcodeimg);
//              }
//              else {
//                  Toast.makeText(QrShow.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
//              }
//          }
//      });//https://firebasestorage.googleapis.com/v0/b/newqrproject-32d8d.appspot.com/o/QR%20CODE%2Fh5DoblzLGugqzMmHNbuI59dCkC92%2F-NV8Uq8p5rUgwzA_TZ8o?alt=media&token=ce53db29-4405-48dc-b1af-64bd5fbd1141





























//        Bundle bundle =getIntent().getExtras();
//        if (bundle!=null)
//        {
//            String name = bundle.getString("evename");
//            String date = bundle.getString("eventdate");
//            String time = bundle.getString("eventtime");
//            String noofperson = bundle.getString("noofperson");
//            rname.setText(name);
//            rdate.setText(date);
//            rtime.setText(time);
//            rnoofper.setText(noofperson);
        }


    }
