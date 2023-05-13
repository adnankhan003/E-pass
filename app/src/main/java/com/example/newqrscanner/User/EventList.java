package com.example.newqrscanner.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.newqrscanner.Modules.QrModule;
import com.example.newqrscanner.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    List<QrModule> qrModuleList;
    EventListAdapter eventListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        recyclerView = findViewById(R.id.eventlistrecycle);
        qrModuleList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        eventListAdapter = new EventListAdapter(this,qrModuleList);
        recyclerView.setAdapter(eventListAdapter);
        reference = FirebaseDatabase.getInstance().getReference("QRLIST");
        AlertDialog.Builder buildert = new AlertDialog.Builder(EventList.this);
        buildert.setCancelable(true);
        buildert.setTitle("GET DATA");
        buildert.setMessage("Loading");
        Dialog dialog = buildert.create();
        dialog.show();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                qrModuleList.clear();

                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {

                    QrModule qrModule = itemsnapshot.getValue(QrModule.class);
                    qrModuleList.add(qrModule);
                }
                eventListAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(EventList.this, "error", Toast.LENGTH_SHORT).show();
                Toast.makeText(EventList.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}