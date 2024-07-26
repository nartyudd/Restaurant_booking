package com.example.booking_restaurant.views.adapter_admins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Restaurant;

import java.util.List;

public class ListRestaurantAdapter extends RecyclerView.Adapter<ListRestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> _restaurants;

    public ListRestaurantAdapter(List<Restaurant> restaurants){
        this._restaurants = restaurants;
    }
    private UpdateClickListener updateClickListener;
    private DeleteClickListener deleteClickListener;
    public void setUpdateClickListener(UpdateClickListener updateClickListener){
        this.updateClickListener = updateClickListener;
    }

    public void setDeleteClickListener(DeleteClickListener deleteClickListener){
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_admin, parent, false);

        return new ListRestaurantAdapter.RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant res = _restaurants.get(position);

        holder.txtName.setText(res.getName());
        holder.txtAddress.setText(res.getAddress());
        holder.txtRating.setText("Đánh giá : " + res.getRating());
        Glide.with(holder.itemView)
                .load(res.getUriImage())
                .into(holder.imageRestaurant);

        holder.btnUpdateRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateClickListener.onUpdateClick(res);
            }
        });

        holder.btnDeleteRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClickListener.onDeleteClick(res.getUuid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return _restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtAddress, txtRating;
        private ImageView imageRestaurant;
        private Button btnUpdateRestaurant, btnDeleteRestaurant;

        public RestaurantViewHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtRating = itemView.findViewById(R.id.txtRating);
            imageRestaurant = itemView.findViewById(R.id.imageRestaurant);
            btnUpdateRestaurant = itemView.findViewById(R.id.btnUpdateRestaurant);
            btnDeleteRestaurant = itemView.findViewById(R.id.btnDeleteRestaurant);
        }
    }

    public interface UpdateClickListener {
        void onUpdateClick(Restaurant res);
    }

    public interface DeleteClickListener{
        void onDeleteClick(String uuid);
    }
}
