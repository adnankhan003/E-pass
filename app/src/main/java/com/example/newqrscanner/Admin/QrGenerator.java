package com.example.newqrscanner.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.newqrscanner.Modules.QrModule;
import com.example.newqrscanner.R;
import com.example.newqrscanner.User.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QrGenerator extends AppCompatActivity {

    EditText gname,gdate,gtime,gnoofper;
    Button generaatebtn;
    ImageView imageView;

    FirebaseAuth auth;

    DatabaseReference reference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.firstmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutmenu:
                Intent intent = new Intent(getBaseContext(), AdminLogin.class);
                startActivity(intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);


        gname = findViewById(R.id.qrgname);
        gdate = findViewById(R.id.qrdate);
        gtime = findViewById(R.id.qrtime);
        gnoofper = findViewById(R.id.qrnofoper);

        generaatebtn = findViewById(R.id.generatebtn);






        gdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentdate = Calendar.getInstance();
                Date cdat= currentdate.getTime();
                SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdfm = new SimpleDateFormat("MM");
                SimpleDateFormat sdfd = new SimpleDateFormat("dd");
                String yy = sdfy.format(cdat);
                String mm = sdfm.format(cdat);
                String dd = sdfd.format(cdat);
                DatePickerDialog datepicker = new DatePickerDialog(QrGenerator.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        gdate.setText(String.valueOf(dayOfMonth)+"-"+String.valueOf(month)+"-"+String.valueOf(year));

                    }
                },Integer.valueOf(yy),Integer.valueOf(mm)-1,Integer.valueOf(dd));
                datepicker.show();
            }
        });

        gtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentdate = Calendar.getInstance();
                Date cdat= currentdate.getTime();
                SimpleDateFormat sdfh = new SimpleDateFormat("HH");
                SimpleDateFormat sdfm = new SimpleDateFormat("mm");
                //SimpleDateFormat sdfd = new SimpleDateFormat("dd");
                String hh = sdfh.format(cdat);
                String mm = sdfm.format(cdat);
             //   String dd = sdfd.format(cdat);
                TimePickerDialog timepicker = new TimePickerDialog(QrGenerator.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        gtime.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
                    }
                },Integer.valueOf(hh),Integer.valueOf(mm),true);
                timepicker.show();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("QRLIST");
        auth = FirebaseAuth.getInstance();

        generaatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String userid = auth.getCurrentUser().getUid();
                String userid = reference.push().getKey();


                // qrCodeGenerate();
                String gnametxt = gname.getText().toString();
                String gdatetxt = gdate.getText().toString();
                String gtimetxt = gtime.getText().toString();
                String gnoofpertxt = gnoofper.getText().toString();
                if (gnametxt.isEmpty() || gdatetxt.isEmpty() || gtimetxt.isEmpty() || gnoofpertxt.isEmpty()) {
                    Toast.makeText(QrGenerator.this, "please enter all the text", Toast.LENGTH_SHORT).show();
                }
                else {


                    QrModule qrModule = new QrModule(gnametxt, gdatetxt, gtimetxt, gnoofpertxt,userid);

                    reference.child(userid).setValue(qrModule);
                    Toast.makeText(QrGenerator.this, "QRCODE GENERATE SUCCESFULLY", Toast.LENGTH_SHORT).show();

//                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//                    try {
//                        BitMatrix bitMatrix = multiFormatWriter.encode(userid, BarcodeFormat.QR_CODE, 600, 600);
//                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//                        imageView.setImageBitmap(bitmap);
//                        imageView.setVisibility(View.VISIBLE);
//
//
//                    } catch (WriterException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });

    }

    }
