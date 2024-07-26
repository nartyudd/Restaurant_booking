package com.example.booking_restaurant.views.adapter_admins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Region;

import java.util.List;

public class ListTableRegAdapter extends RecyclerView.Adapter<ListTableRegAdapter.ListTableRegViewHolder>{

    private List<Region> _regions;
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ListTableRegAdapter(List<Region> regions){
        this._regions = regions;
    }

    @NonNull
    @Override
    public ListTableRegViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_region, parent, false);
        return new ListTableRegAdapter.ListTableRegViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTableRegViewHolder holder, int position) {
        Region _reg = _regions.get(position);

        holder.txtRegion.setText(_reg.getName().toString());

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(_reg.getUuid(), _reg.getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return _regions.size();
    }

    public class ListTableRegViewHolder extends RecyclerView.ViewHolder {

        private TextView txtRegion;
        private LinearLayout linear;

        public ListTableRegViewHolder(View itemView){
            super(itemView);

            txtRegion = itemView.findViewById(R.id.txtRegion);
            linear = itemView.findViewById(R.id.linear);

        }
    }


    public interface ClickListener{
        void onClick(String regionId, String regionName);
    }
}
