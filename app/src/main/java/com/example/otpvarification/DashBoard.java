package com.example.otpvarification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otpvarification.Models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoard extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    TextView username,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        username=findViewById(R.id.UserName);
        number=findViewById(R.id.Mobile);
        String name=getIntent().getStringExtra("UserName");
        String n=getIntent().getStringExtra("Number");
        username.setText(name);
        number.setText(n);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        String ids=getIntent().getStringExtra("id");
        //Toast.makeText(this, ids, Toast.LENGTH_SHORT).show();
        Users user=new Users(name,n);
        database.getReference().child("Users").child(ids).setValue(user);

    }
}