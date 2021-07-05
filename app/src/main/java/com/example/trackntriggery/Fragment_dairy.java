package com.example.trackntriggery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Fragment_dairy extends Fragment {
    RecyclerView recyclerView;
    List<diary_model> list;
    View v;
    diary_fireAdapter adaPter;

    public Fragment_dairy(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_dairy,container,false);
        recyclerView=v.findViewById(R.id.recyclerView_diary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*Adapter adapter=new Adapter(getContext(),list);
        recyclerView.setAdapter(adapter);*/

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseRecyclerOptions<diary_model> options =
                new FirebaseRecyclerOptions.Builder<diary_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Diary").child(user.getUid()), diary_model.class)
                        .build();
        adaPter=new diary_fireAdapter(options);
        recyclerView.setAdapter(adaPter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //list=new ArrayList<>();
        //list.add(new diary_model("Apple",20,"dbsdchbdhcdsb"));

        list=new ArrayList<>();
        /*list.add(new diary_model("Apple","20","dbsdchbdhcdsb"));
        list.add(new diary_model("Banana","30","dbsdchbdhcdsb"));
        list.add(new diary_model("Grapes","40","dbsdchbdhcdsb"));*/
        /*Adapter adapter=new Adapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(adapter);*/
        /*FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseRecyclerOptions<diary_model> options =
                new FirebaseRecyclerOptions.Builder<diary_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Appliances").child(user.getUid()), diary_model.class)
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
