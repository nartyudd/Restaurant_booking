package com.example.booking_restaurant.views.adapter_users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.ChooseTableModel;

import java.util.List;

public class ChooseTableAdapter extends RecyclerView.Adapter<ChooseTableAdapter.ViewHoder> {

    List<ChooseTableModel> list;

    public ChooseTableAdapter(List<ChooseTableModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_choose_table_item,parent,false);
        return new ChooseTableAdapter.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {


        holder.imageView.setImageResource(list.get(position).getImg());
        holder.description.setText(list.get(position).getDescription());
        holder.status.setText(list.get(position).getStatus());
        holder.name.setText(list.get(position).getNameTable());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView description,status,name;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewTable);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            name = itemView.findViewById(R.id.tableName);
        }
    }
}
