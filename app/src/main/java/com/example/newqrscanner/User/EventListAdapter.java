package com.example.newqrscanner.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newqrscanner.Modules.QrModule;
import com.example.newqrscanner.Modules.RequestModule;
import com.example.newqrscanner.Modules.UserModules;
import com.example.newqrscanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {
    Context context;
    List<QrModule> qrModuleList;

    public EventListAdapter(Context context, List<QrModule> qrModuleList) {
        this.context = context;
        this.qrModuleList = qrModuleList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_registerview,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.title.setText(qrModuleList.get(position).getTittle());
        holder.date.setText(qrModuleList.get(position).getDate());
        holder.time.setText(qrModuleList.get(position).getTime());
        holder.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser auth;
                auth = FirebaseAuth.getInstance().getCurrentUser();
                String eventid = qrModuleList.get(holder.getAdapterPosition()).getEventid();
           //     DatabaseReference reference = FirebaseDatabase.getInstance().getReference("REQUEST LIST");
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("USERS");
                reference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModules modules = snapshot.getValue(UserModules.class);
                        String name = modules.getName();
                        String phno = modules.getMobileNo();
                        String email = modules.getGmail();
                        String approvedstatus = "pending";

                        RequestModule requestModule = new RequestModule(name,email,phno,approvedstatus,auth.getUid());
                        DatabaseReference reqreference = FirebaseDatabase.getInstance().getReference("REQUEST");
                        reqreference.child(eventid).child(auth.getUid()).setValue(requestModule).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(context, "REQUEST SEND", Toast.LENGTH_SHORT).show();
                               holder.registerbtn.setEnabled(false);
                           }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//                UserModules modules = new UserModules()
//               reference.child(eventid).child(auth.getUid()).
            //    Toast.makeText(context,auth.getUid(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return qrModuleList.size();
    }
}
class EventViewHolder extends RecyclerView.ViewHolder{
    CardView regcardview;
    TextView title,date,time;
    Button registerbtn;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        regcardview = itemView.findViewById(R.id.cardregister);
        title = itemView.findViewById(R.id.titlecardre);
        date = itemView.findViewById(R.id.datecardre);
        time = itemView.findViewById(R.id.timecardre);
        registerbtn = itemView.findViewById(R.id.cardregbtn);


    }
}
