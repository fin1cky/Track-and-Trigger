package com.example.trackntriggery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<model_inventory> data;
    Context mContext;
    public Adapter(Context mContext,List<model_inventory> data){
        this.layoutInflater=LayoutInflater.from(mContext);
        this.mContext=mContext;
        this.data=data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.custom_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameOfItem.setText(data.get(position).getName());
        holder.quantity.setText(data.get(position).getQuantity());

        //add here quantity,image
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfItem,quantity;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfItem=itemView.findViewById(R.id.nameOfItem);
            quantity=itemView.findViewById(R.id.quantity);
            imageView=itemView.findViewById(R.id.image_url);
        }
    }
}
