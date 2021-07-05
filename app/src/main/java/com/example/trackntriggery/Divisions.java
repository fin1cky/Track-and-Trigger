package com.example.trackntriggery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Divisions extends AppCompatActivity {
    Button logout,track_in;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String profession;

    TextView prof;
    FloatingActionButton extra,b1,b2,b3 ;
    TextView t1,t2;
    Button Trigger,Diary,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        b4=findViewById(R.id.BrowserV);
        logout=findViewById(R.id.logout_divisions);
        logout.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        //name =findViewById(R.id.name);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        extra = findViewById(R.id.Button);
        t1 = findViewById(R.id.Track_text);
        t2 = findViewById(R.id.Bills_text);
        b1 = findViewById(R.id.Track_fab);
        b2 = findViewById(R.id.Bills_fab);
        b3 = findViewById(R.id.button2);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        extra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),browser_2.class));
            }
        });
        Trigger=findViewById(R.id.Trigger_button);
        Diary=findViewById(R.id.Diary_button);
        Trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aryaman add here
            }
        });
        Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Dairy_tab.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Track_tabbed.class));
            }
        });
        /*track_in=findViewById(R.id.track_button);
        track_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Track_tabbed.class));
            }
        });
        prof=findViewById(R.id.prof);*/
        /*databaseReference=FirebaseDatabase.getInstance().getReference();
        user=FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.child("Users").child(user.getUid()).child("profession").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    profession=snapshot.getValue().toString();
                    prof.setText("You have set your profession as :"+profession);
                }
                else{
                    //Toast.makeText(Divisions.this, "no snapshot exists", Toast.LENGTH_SHORT).show();
                    //System.out.println(profession);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        //System.out.println(profession);

    }
}