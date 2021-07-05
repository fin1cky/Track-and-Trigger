package com.example.trackntriggery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    List<model_inventory> model_inventoryList;
    private View v;
    fireAdapter adaPter;

    public Fragment2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment2_layout,container,false);
        recyclerView= v.findViewById(R.id.recyclerView_appliances_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*Adapter adapter=new Adapter(getContext(),model_inventoryList);
        recyclerView.setAdapter(adapter);*/
        /*Adapter adapter=new Adapter(getContext(),model_inventoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);*/

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseRecyclerOptions<model_inventory> options =
                new FirebaseRecyclerOptions.Builder<model_inventory>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Appliances").child(user.getUid()), model_inventory.class)
                        .build();
        adaPter=new fireAdapter(options);
        recyclerView.setAdapter(adaPter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseRecyclerOptions<model_inventory> options =
                new FirebaseRecyclerOptions.Builder<model_inventory>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Appliances").child(user.getUid()), model_inventory.class)
                        .build();
        adaPter=new fireAdapter(options);*/
        /*if (adaPter==null){
            Log.d("TAG","adaPter is null");
        }
        else if(recyclerView==null){
            Log.d("REC","recyclerview is null");
        }
        else{
            recyclerView.setAdapter(adaPter);
        }*/

        //recyclerView.setAdapter(adaPter);
        model_inventoryList=new ArrayList<>();
        /*model_inventoryList.add(new model_inventory("Apple","20","dbsdchbdhcdsb"));
        model_inventoryList.add(new model_inventory("Banana","30","dbsdchbdhcdsb"));
        model_inventoryList.add(new model_inventory("Grapes","40","dbsdchbdhcdsb"));*/

    }

    @Override
    public void onStart() {
        super.onStart();
        adaPter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adaPter.stopListening();
    }


}
