package com.example.trackntriggery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.trackntriggery.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dairy_tab extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdaptery adaptery;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_tab);
        /*SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        tabLayout = findViewById(R.id.tabs);


        FloatingActionButton fab = findViewById(R.id.fab_diary);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),new_diary_2.class));
            }
        });
        adaptery = new ViewPagerAdaptery(getSupportFragmentManager());

        adaptery.AddFragment(new Fragment_dairy(),"Diary");
        //adaptery.AddFragment(new Fragment2(),"Appliances");
        //adaptery.AddFragment(new Fragment3(),"Reports");
        viewPager.setAdapter(adaptery);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ffffff"));
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        diary_model d1=new diary_model("Title of diary","28/11/2020","This is a sample diary to show working of diary feature in this app");

        databaseReference.child("Diary").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChildren()){
                    databaseReference.child("Diary").child(user.getUid()).child(d1.getTitle()).setValue(d1);
                    //databaseReference.child("Appliances").child(user.getUid()).child(apl2.getName()).setValue(apl2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dairy_tab.this, "already has a child", Toast.LENGTH_SHORT).show();
            }
        });
    }
}