package com.example.trackntriggery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Track extends AppCompatActivity {
    RecyclerView recyclerView_groceries,recyclerView_appliances;
    Adapter adapter_groceries,adapter_appliances;
    ArrayList<String> arrayList_groceries,arrayList_appliances;
    Button l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        recyclerView_groceries=findViewById(R.id.recyclerView_groceries);
        recyclerView_appliances=findViewById(R.id.recyclerView_appliances);
        arrayList_groceries=new ArrayList<>();
        arrayList_appliances=new ArrayList<>();
        arrayList_groceries.add("1st card");
        arrayList_groceries.add("2nd card");
        arrayList_groceries.add("3rd card");
        arrayList_groceries.add("4th card");
        arrayList_groceries.add("4th card");
        arrayList_groceries.add("4th card");
        arrayList_groceries.add("4th card");

        arrayList_appliances.add("1st card");
        arrayList_appliances.add("2nd card");
        recyclerView_groceries.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_appliances.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView_appliances.setBackgroundColor(50);
        //******adapter_groceries=new Adapter(this,arrayList_groceries);
        recyclerView_groceries.setAdapter(adapter_groceries);

        //******adapter_appliances=new Adapter(this,arrayList_appliances);
        recyclerView_appliances.setAdapter(adapter_appliances);
        l=findViewById(R.id.logout_track);
        //name =findViewById(R.id.name);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}