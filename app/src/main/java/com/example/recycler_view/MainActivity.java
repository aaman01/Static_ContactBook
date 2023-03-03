package com.example.recycler_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<contactModel>arrContact= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv=findViewById(R.id.RV);
        FloatingActionButton btn= findViewById(R.id.btnfloat);

        arrContact.add(new contactModel(R.drawable.a,"Aditya","6206980298"));
        arrContact.add(new contactModel(R.drawable.b,"S","3216549870"));
        arrContact.add(new contactModel(R.drawable.c,"V","456891235"));
        arrContact.add(new contactModel(R.drawable.d,"R","445678123"));
        arrContact.add(new contactModel(R.drawable.e,"T","6549873210"));
        arrContact.add(new contactModel(R.drawable.f,"Y","789456123"));
        arrContact.add(new contactModel(R.drawable.so,"Soumya","9334989551"));
        arrContact.add(new contactModel(R.drawable.d,"I","5689231470"));
        arrContact.add(new contactModel(R.drawable.e,"J","8754213690"));
        arrContact.add(new contactModel(R.drawable.f,"K","7946138522"));
        arrContact.add(new contactModel(R.drawable.b,"M","0258147777"));
        arrContact.add(new contactModel(R.drawable.f,"Y","789456123"));
        arrContact.add(new contactModel(R.drawable.d,"R","445678123"));
        arrContact.add(new contactModel(R.drawable.d,"I","5689231470"));
        arrContact.add(new contactModel(R.drawable.e,"J","8754213690"));
        arrContact.add(new contactModel(R.drawable.f,"K","7946138522"));

        rv.setLayoutManager(new LinearLayoutManager(this));

        Adapter adapter= new Adapter(this, arrContact);

        rv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog= new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);

                EditText dname=dialog.findViewById(R.id.dialogname);
                EditText dnumber=dialog.findViewById(R.id.dialognumber);
                Button dialoguebtn=dialog.findViewById(R.id.dialog_btn);

                dialoguebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        String number= "";
                        if(!dname.getText().toString().equals("")){
                            name=dname.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter The Name", Toast.LENGTH_SHORT).show();
                        }

                        if (!dnumber.getText().toString().equals("")){
                            number=dnumber.getText().toString();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Please Enter the number", Toast.LENGTH_SHORT).show();
                        }
                        arrContact.add(new contactModel(name,number));
                       adapter.notifyItemChanged(arrContact.size()-1);
                       rv.scrollToPosition(arrContact.size()-1);

                       dialog.dismiss();
                    }


                });

dialog.show();
            }
        });





    }
}