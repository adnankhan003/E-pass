package com.example.newqrscanner.Admin;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newqrscanner.Modules.EventAndUserModule;
import com.example.newqrscanner.Modules.QrModule;
import com.example.newqrscanner.Modules.RequestModule;
import com.example.newqrscanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdminRequestListAdapter extends RecyclerView.Adapter<AdminRequestListAdapter.AdminRequestListViewHolder> {
    Context context;
    List<RequestModule> requestModuleList;
    String eventid;

    public AdminRequestListAdapter(Context context, List<RequestModule> requestModuleList, String eventid) {
        this.context = context;
        this.requestModuleList = requestModuleList;
        this.eventid = eventid;
    }



    @NonNull
    @Override
    public AdminRequestListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_admin_request_view,parent,false);
        return new AdminRequestListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRequestListViewHolder holder, int position) {
        holder.name.setText(requestModuleList.get(position).getName());
        holder.email.setText(requestModuleList.get(position).getGmail());
        holder.phoneno.setText(requestModuleList.get(position).getMobileNo());
        holder.status.setText(requestModuleList.get(position).getApprovedstatus());
        String userid = requestModuleList.get(holder.getAdapterPosition()).getUserid();
//         : requestModuleList.size()

        for (int i=0;i<requestModuleList.size();i++){
            String stat= holder.status.getText().toString();
            requestModuleList.get(i).getApprovedstatus();
    //        Toast.makeText(context,  stat, Toast.LENGTH_SHORT).show();
            if (stat.equals("Rejected")){
                holder.reject.setEnabled(false);
            }if (stat.equals("Accepted")){
                holder.accept.setEnabled(false);
            }
        }
       // DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("REQUEST");

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String merggeusereve = userid+eventid;

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("QRLIST");
                reference1.child(eventid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        QrModule module = snapshot.getValue(QrModule.class);
                       String title =  module.getTittle();
                       String date =  module.getDate();
                       String time =  module.getTime();
                        EventAndUserModule eventAndUserModule = new EventAndUserModule(title,date,time,eventid,holder.name.getText().toString(),holder.phoneno.getText().toString(),holder.email.getText().toString(),userid);
                        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("ACCEPT LIST");
                        reference3.child(userid).child(eventid).setValue(eventAndUserModule);
                        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("QRSCANNER");
                        reference4.child(merggeusereve).setValue(eventAndUserModule);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("REQUEST");
                //      reference.child(eventid).child(userid).removeValue();
                reference.child(eventid).child(userid).child("approvedstatus").setValue("Accepted").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "Accepted successfully", Toast.LENGTH_SHORT).show();
                            holder.accept.setEnabled(false);
                            holder.reject.setEnabled(true);
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                   // String merggeusereve = userid+eventid;

                    BitMatrix bitMatrix = multiFormatWriter.encode(merggeusereve, BarcodeFormat.QR_CODE, 600, 600);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference("QR CODE");
                    storageReference.child(merggeusereve).putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                          Toast.makeText(context, "Successfully Qr created", Toast.LENGTH_SHORT).show();
//                          Task<Uri> downloadurl = taskSnapshot.getStorage().getDownloadUrl();
//                          if (downloadurl.isSuccessful()){
//                              String generated = downloadurl.getResult().toString();
//                              System.out.println("your link is "+generated);
//                              Toast.makeText(context, generated, Toast.LENGTH_SHORT).show();
//                          }else{
//                              Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                          }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch(Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                   }
                        else {
                            Toast.makeText(context, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//
//
//                }
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = requestModuleList.get(holder.getAdapterPosition()).getUserid();
            //    Toast.makeText(context, "reject"+userid, Toast.LENGTH_SHORT).show();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("REQUEST");
          //      reference.child(eventid).child(userid).removeValue();
                reference.child(eventid).child(userid).child("approvedstatus").setValue("Rejected").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "rejected successfully", Toast.LENGTH_SHORT).show();
                            holder.reject.setEnabled(false);
                            holder.accept.setEnabled(true);
                        }
                        else {
                            Toast.makeText(context, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return requestModuleList.size();
    }

    public class AdminRequestListViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,phoneno,status;
        Button accept,reject;
        public AdminRequestListViewHolder(@NonNull View itemView) {
            super(itemView);
            name    = itemView.findViewById(R.id.reqname);
            email   = itemView.findViewById(R.id.reqgmail);
            phoneno = itemView.findViewById(R.id.reqphno);
            status  = itemView.findViewById(R.id.reqstatus);
            accept  = itemView.findViewById(R.id.aacptbtn);
            reject  = itemView.findViewById(R.id.arejbtn);
        }
    }
}
