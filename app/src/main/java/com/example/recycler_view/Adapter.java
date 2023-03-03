package com.example.recycler_view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<contactModel> arr;

    Adapter(Context context, ArrayList<contactModel> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textnumber.setText(arr.get(position).number);
        holder.img.setImageResource(arr.get(position).img);
        holder.textname.setText(arr.get(position).name);
        //update
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog2 = new Dialog(context);
                dialog2.setContentView(R.layout.update_diallog);

                EditText updatename = dialog2.findViewById(R.id.updatename);
                EditText updatenumber = dialog2.findViewById(R.id.updatenumber);
                Button Updatebtn= dialog2.findViewById(R.id.update_btn);

                updatename.setText(arr.get(holder.getAdapterPosition()).name);
                updatenumber.setText(arr.get(holder.getAdapterPosition()).number);

                Updatebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="";
                        String number="";
                        if(!updatename.getText().toString().equals("")){
                            name=updatename.getText().toString();
                        }else{
                            name=updatenumber.getText().toString();
                        }

                        if(!updatenumber.getText().toString().equals("")){
                            number=updatenumber.getText().toString();
                        }else{
                            Toast.makeText(context, "PLease input valid phone number", Toast.LENGTH_SHORT).show();
                        }
                        arr.set(holder.getAdapterPosition(), new contactModel(name,number));
                      notifyItemChanged(holder.getAdapterPosition());
                        dialog2.dismiss();

                    }
                });


                dialog2.show();
            }

        });
              //delete
        holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
           AlertDialog.Builder builder= new AlertDialog.Builder(context);

           builder.setTitle("Delete This contact");
           builder.setMessage("Are you sure");
           builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                       }
                   })

                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                         arr.remove(holder.getAdapterPosition());
                         notifyItemChanged(holder.getAdapterPosition());
                       }
                   });

                   builder.show();
return true;




            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TextView textnumber, textname;
        ImageView img;
        LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);

            textnumber = itemView.findViewById(R.id.textnumber);
            textname = itemView.findViewById(R.id.textName);
            img = itemView.findViewById(R.id.imagecontact);
            ll = itemView.findViewById(R.id.llrow);


        }
    }
}
