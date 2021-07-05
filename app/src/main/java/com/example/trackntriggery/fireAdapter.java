package com.example.trackntriggery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fireAdapter extends FirebaseRecyclerAdapter<model_inventory,fireAdapter.myViewholder>{
    public fireAdapter(@NonNull FirebaseRecyclerOptions<model_inventory> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull model_inventory model) {
        holder.nameOfItem.setText(model.getName());
        holder.quantity.setText(model.getQuantity());
        //Log.d("getCat",model.getCategory());
        //Log.d("uid",user.getUid());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                int q;
                q=Integer.parseInt(model.getQuantity());
                q=q+Integer.parseInt(holder.newq.getText().toString());
                String qs=""+q;
                model.setQuantity(qs);
                //Log.d("getCat",model.getCategory());
                //Log.d("uid",user.getUid());
                //model_inventory childUpdates=model;
                if(user!=null){

                    //databaseReference.child(model.getCategory()).child(user.getUid()).child(model.getName()).setValue(new model_inventory(model.getName(),qs,model.getImage_url(),model.getCategory()));
                    holder.quantity.setText(qs);
                }

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                int q;
                q=Integer.parseInt(model.getQuantity());
                q=q-Integer.parseInt(holder.newq.getText().toString());
                String qs=""+q;
                model.setQuantity(qs);
                //databaseReference.child(model.getCategory()).child(user.getUid()).child(model.getName()).child("quantity").setValue("qs");
                holder.quantity.setText(qs);
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        TextView nameOfItem,quantity;
        Button plus,minus;
        EditText newq;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            nameOfItem=(TextView) itemView.findViewById(R.id.nameOfItem);
            quantity=(TextView) itemView.findViewById(R.id.quantity);
            plus=(Button) itemView.findViewById(R.id.plus);
            minus=(Button) itemView.findViewById(R.id.minus);
            newq=(EditText) itemView.findViewById(R.id.editTextNumber);
        }
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();

    }
}
