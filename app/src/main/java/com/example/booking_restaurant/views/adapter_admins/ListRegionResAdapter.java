package com.example.booking_restaurant.views.adapter_admins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ListRegionResAdapter extends RecyclerView.Adapter<ListRegionResAdapter.ViewHolder>{

    private List<Restaurant> _restaurant;

    private ReadClickListener readClickListener;

    public void setReadClickListener(ReadClickListener readClickListener) {
        this.readClickListener = readClickListener;
    }

    public ListRegionResAdapter(List<Restaurant> _restaurant){
        this._restaurant = _restaurant;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_restaurant_region, parent, false);
        return new ListRegionResAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant res = _restaurant.get(position);

        holder.txtName.setText(res.getName());
        holder.txtAddress.setText(res.getAddress());

        holder.cardRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readClickListener.onReadClick(res.getUuid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return _restaurant.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtAddress;
        private MaterialCardView cardRegion;
        public ViewHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            cardRegion = itemView.findViewById(R.id.cardRegion);

        }
    }

    public interface ReadClickListener {
        void onReadClick(String idRestaurant);
    }
}
