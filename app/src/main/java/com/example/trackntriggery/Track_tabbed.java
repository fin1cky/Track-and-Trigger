package com.example.trackntriggery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.trackntriggery.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Track_tabbed extends AppCompatActivity {
    RecyclerView recyclerView_groceries,recyclerView_appliances,recyclerView_reports;
    Adapter adapter_groceries,adapter_appliances,adapter_reports;
    ArrayList<String> arrayList_groceries,arrayList_appliances;
    Button l;

    private ViewPager viewPager;
    private ViewPagerAdaptery adaptery;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_tabbed);
        //SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        tabLayout = findViewById(R.id.tabs);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                 */
                startActivity(new Intent(getApplicationContext(),New_Item.class));
            }
        });

        adaptery = new ViewPagerAdaptery(getSupportFragmentManager());

        adaptery.AddFragment(new Fragment1(),"Groceries");
        adaptery.AddFragment(new Fragment2(),"Appliances");
        adaptery.AddFragment(new Fragment3(),"Reports");
        viewPager.setAdapter(adaptery);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ffffff"));
        model_inventory apl1=new model_inventory("TV","1","dvf","Appliances");
        model_inventory apl2=new model_inventory("Dining Table","1","dvf","Appliances");

        model_inventory g1=new model_inventory("Rice","2","dvf","Groceries");
        model_inventory g2=new model_inventory("Wheat","3","dvf","Groceries");
        model_inventory g3=new model_inventory("Salt","1","dvf","Groceries");
        model_inventory g4=new model_inventory("Apples","3","dvf","Groceries");
        model_inventory g5=new model_inventory("Biscuits","5","dvf","Groceries");
        model_inventory g6=new model_inventory("Milk packets","1","dvf","Groceries");

        model_inventory r1=new model_inventory("Yashoda Hospital","1","dvf","Reports");
        model_inventory r2=new model_inventory("Local Hospital","3","dvf","Reports");
        model_inventory r3=new model_inventory("Government Hospital","1","dvf","Reports");
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Appliances").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChildren()){
                    databaseReference.child("Appliances").child(user.getUid()).child(apl1.getName()).setValue(apl1);
                    databaseReference.child("Appliances").child(user.getUid()).child(apl2.getName()).setValue(apl2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Groceries").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChildren()){
                    databaseReference.child("Groceries").child(user.getUid()).child(g1.getName()).setValue(g1);
                    databaseReference.child("Groceries").child(user.getUid()).child(g2.getName()).setValue(g2);
                    databaseReference.child("Groceries").child(user.getUid()).child(g3.getName()).setValue(g3);
                    databaseReference.child("Groceries").child(user.getUid()).child(g4.getName()).setValue(g4);
                    databaseReference.child("Groceries").child(user.getUid()).child(g5.getName()).setValue(g5);
                    databaseReference.child("Groceries").child(user.getUid()).child(g6.getName()).setValue(g6);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Reports").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChildren()){
                    databaseReference.child("Reports").child(user.getUid()).child(r1.getName()).setValue(r1);
                    databaseReference.child("Reports").child(user.getUid()).child(r2.getName()).setValue(r2);
                    databaseReference.child("Reports").child(user.getUid()).child(r3.getName()).setValue(r3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}