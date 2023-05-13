package com.example.newqrscanner.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newqrscanner.Modules.QrModule;
import com.example.newqrscanner.R;

import java.util.List;

public class AdminEventListAdapter extends RecyclerView.Adapter<AdminEventListViewHolder> {

    Context context;
    List<QrModule> qrModuleList;

    public AdminEventListAdapter(Context context, List<QrModule> qrModuleList) {
        this.context = context;
        this.qrModuleList = qrModuleList;
    }

    @NonNull
    @Override
    public AdminEventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_admin_evenlist,parent,false);
        return new AdminEventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminEventListViewHolder holder, int position) {

        holder.title.setText(qrModuleList.get(position).getTittle());
        holder.date.setText(qrModuleList.get(position).getDate());
        holder.time.setText(qrModuleList.get(position).getTime());
        holder.requestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AdminRequestList.class);
                intent.putExtra("eventid",qrModuleList.get(holder.getAdapterPosition()).getEventid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qrModuleList.size();
    }
}
class AdminEventListViewHolder extends RecyclerView.ViewHolder{

    CardView regcardview;
    TextView title,date,time;
    Button requestbtn;

    public AdminEventListViewHolder(@NonNull View itemView) {
        super(itemView);
        regcardview = itemView.findViewById(R.id.acardregister);
        title = itemView.findViewById(R.id.atitlecardre);
        date = itemView.findViewById(R.id.adatecardre);
        time = itemView.findViewById(R.id.atimecardre);
        requestbtn = itemView.findViewById(R.id.acardreqbtn);
    }
}
