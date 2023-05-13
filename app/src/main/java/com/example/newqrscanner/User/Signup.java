package com.example.newqrscanner.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newqrscanner.Modules.UserModules;
import com.example.newqrscanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText sname,sgmail,sphoneno,spass;
    Button signupbtn;
    TextView gotologin;

    FirebaseAuth auth;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sname = findViewById(R.id.sname);
        sphoneno = findViewById(R.id.sphoneno);
        spass = findViewById(R.id.spass);
        signupbtn = findViewById(R.id.signupbtn);
        gotologin = findViewById(R.id.gotologin);
        sgmail = findViewById(R.id.sgmail);
        auth = FirebaseAuth.getInstance();


        databaseReference = FirebaseDatabase.getInstance().getReference("USERS");

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snametxt = sname.getText().toString();
                String sgmailtxt = sgmail.getText().toString();
                String sphonenotxt = sphoneno.getText().toString();
                String spasstxt = spass.getText().toString();
                if (snametxt.isEmpty() || sgmailtxt.isEmpty() || sphonenotxt.isEmpty() || spasstxt.isEmpty())
                 {
                    Toast.makeText(Signup.this, "please fill all the details", Toast.LENGTH_SHORT).show();
                }else {

                    auth.createUserWithEmailAndPassword(sgmailtxt, spasstxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                UserModules userModules = new UserModules(snametxt, sgmailtxt, sphonenotxt, spasstxt);
                                databaseReference.child(auth.getCurrentUser().getUid()).setValue(userModules);

                                Toast.makeText(Signup.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this, Login.class);
                                startActivity(intent);
                                finish();
                                auth.signOut();
                            } else {
                                Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}