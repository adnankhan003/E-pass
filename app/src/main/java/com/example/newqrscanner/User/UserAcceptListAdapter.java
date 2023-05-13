package com.example.newqrscanner.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newqrscanner.Modules.EventAndUserModule;
import com.example.newqrscanner.R;

import java.util.ConcurrentModificationException;
import java.util.List;

public class UserAcceptListAdapter extends RecyclerView.Adapter<UserAcceptListMyViewHolder> {

    Context context;
    List<EventAndUserModule> eventAndUserModules;

    public UserAcceptListAdapter(Context context, List<EventAndUserModule> eventAndUserModules) {
        this.context = context;
        this.eventAndUserModules = eventAndUserModules;
    }

    @NonNull
    @Override
    public UserAcceptListMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_useracceptlist,parent,false);
        return new UserAcceptListMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAcceptListMyViewHolder holder, int position) {
        holder.title.setText(eventAndUserModules.get(position).getEventname());
        holder.ualcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,QrShow.class);
                intent.putExtra("evename",eventAndUserModules.get(holder.getAdapterPosition()).getEventname());
                intent.putExtra("eventdate",eventAndUserModules.get(holder.getAdapterPosition()).getEventdate());
                intent.putExtra("eventtime",eventAndUserModules.get(holder.getAdapterPosition()).getEventtime());
                intent.putExtra("eventid",eventAndUserModules.get(holder.getAdapterPosition()).getEventid());
                intent.putExtra("userid",eventAndUserModules.get(holder.getAdapterPosition()).getUserid());
              //  intent.putExtra("evename",eventAndUserModules.get(holder.getAdapterPosition()).getEventname());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventAndUserModules.size();
    }
}
class UserAcceptListMyViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    CardView ualcardView;
    public UserAcceptListMyViewHolder(@NonNull View itemView) {
        super(itemView);
        title =itemView.findViewById(R.id.ualeventname);
        ualcardView = itemView.findViewById(R.id.useracceptlistcard);
    }
}
