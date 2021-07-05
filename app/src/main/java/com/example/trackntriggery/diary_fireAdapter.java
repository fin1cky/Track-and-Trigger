package com.example.trackntriggery;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class diary_fireAdapter extends FirebaseRecyclerAdapter<diary_model,diary_fireAdapter.myViewholder>{
    public diary_fireAdapter(@NonNull FirebaseRecyclerOptions<diary_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull diary_model model) {
        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDate());
        holder.text.setText((model.getText()).substring(0,25)+"...");
        //Log.d("getCat",model.getCategory());
        //Log.d("uid",user.getUid());
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.open.getContext(),diary_full_view.class);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("date",model.getDate());
                intent.putExtra("text",model.getText());
                holder.open.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_view,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        TextView title,date,text;
        Button open;
        ConstraintLayout diary_lay;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_diary);
            date=itemView.findViewById(R.id.date);
            text=itemView.findViewById(R.id.text_diary);
            open=itemView.findViewById(R.id.open_button);
            diary_lay=itemView.findViewById(R.id.diary_lay);

        }
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();

    }


}
