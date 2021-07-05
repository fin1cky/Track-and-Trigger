package com.example.trackntriggery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class OtherPage1 extends AppCompatActivity {
    FloatingActionButton extra,b1,b2,b3;
    TextView t1,t2;
    Button Track,Bills,logout,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_page1);
        extra = findViewById(R.id.Button);
        t1 = findViewById(R.id.TriggerText);
        t2 = findViewById(R.id.tPd);
        b4 = findViewById(R.id.BrowserOther);
        b1 = findViewById(R.id.Trigger);
        b2 = findViewById(R.id.DiaryOther);
        logout=findViewById(R.id.logout_other);
        b4.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3 = findViewById(R.id.button2);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.INVISIBLE);
        extra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
            }
        });
        Track=findViewById(R.id.Trackbtn);
        Bills=findViewById(R.id.BillsReportsOther);
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
        Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Track_tabbed.class));
            }
        });
        logout=findViewById(R.id.logout_other);
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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aryaman add here
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Dairy_tab.class));
            }
        });
    }
}