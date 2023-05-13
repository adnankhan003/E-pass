package com.example.newqrscanner.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.newqrscanner.Admin.AdminRequestList;
import com.example.newqrscanner.Modules.EventAndUserModule;
import com.example.newqrscanner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserAcceptList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    UserAcceptListAdapter addapter;
    FirebaseAuth auth;
    List<EventAndUserModule> moduleslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_accept_list);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("ACCEPT LIST");
        recyclerView = findViewById(R.id.ualrecycle);
        moduleslist= new ArrayList<>();
        addapter = new UserAcceptListAdapter(this,moduleslist);

        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addapter);

        AlertDialog.Builder buildert = new AlertDialog.Builder(UserAcceptList.this);
        buildert.setCancelable(true);
        buildert.setTitle("GET DATA");
        buildert.setMessage("Loading");
        Dialog dialog = buildert.create();
        dialog.show();

        reference.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            moduleslist.clear();
            for (DataSnapshot itemsnapshot : snapshot.getChildren()){
                EventAndUserModule module = itemsnapshot.getValue(EventAndUserModule.class);
                moduleslist.add(module);
            }
            addapter.notifyDataSetChanged();
            dialog.dismiss();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(UserAcceptList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });

    }
}