package com.example.newqrscanner.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newqrscanner.Modules.RequestModule;
import com.example.newqrscanner.R;
import com.example.newqrscanner.User.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminRequestList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RequestModule> requestModuleList;
    DatabaseReference reference;
    AdminRequestListAdapter adapter;
    String eventid;
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
        setContentView(R.layout.activity_admin_request_list);

        recyclerView = findViewById(R.id.adminreqrecycle);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            eventid =bundle.getString("eventid");
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        requestModuleList = new ArrayList<>();
        adapter = new AdminRequestListAdapter(this,requestModuleList,eventid);
        recyclerView.setAdapter(adapter);

        reference = FirebaseDatabase.getInstance().getReference("REQUEST");
        AlertDialog.Builder buildert = new AlertDialog.Builder(AdminRequestList.this);
        buildert.setCancelable(true);
        buildert.setTitle("GET DATA");
        buildert.setMessage("Loading");
        Dialog dialog = buildert.create();
        dialog.show();

        reference.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                requestModuleList.clear();

                for (DataSnapshot itemsnapshot : snapshot.getChildren()){
                    RequestModule requestModule = itemsnapshot.getValue(RequestModule.class);
                    requestModuleList.add(requestModule);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(AdminRequestList.this, "error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }
}