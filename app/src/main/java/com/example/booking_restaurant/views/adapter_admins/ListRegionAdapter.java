package com.example.booking_restaurant.views.adapter_admins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Region;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ListRegionAdapter extends RecyclerView.Adapter<ListRegionAdapter.ListRegionViewHolder> {

    private List<Region> _regions;
    private UpdateClickListener updateClickListener;
    private DeleteClickListener deleteClickListener;

    public void setUpdateClickListener(UpdateClickListener updateClickListener) {
        this.updateClickListener = updateClickListener;
    }

    public void setDeleteClickListener(DeleteClickListener deleteClickListener) {
        this.deleteClickListener = deleteClickListener;
    }


    public ListRegionAdapter(List<Region> regions) {
        this._regions = regions;
    }

    @NonNull
    @Override
    public ListRegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region_admin, parent, false);

        return new ListRegionAdapter.ListRegionViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListRegionViewHolder holder, int position) {
        Region _region = _regions.get(position);

        holder.txtRegion.setText(_region.getName().toUpperCase(Locale.ROOT));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClickListener.onDeleteClick(_region.getUuid());
            }
        });

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = holder.edtUpdate.getText().toString().trim();

                if(name.equals("") || name.isEmpty()){
                    Toast.makeText(holder.itemView.getContext(), "Bạn cần phải điền tên cần thay đổi.!", Toast.LENGTH_SHORT).show();
                } else {
                    holder.edtUpdate.setText(null);
                    holder.txtRegion.setText(name.toUpperCase(Locale.ROOT));
                    updateClickListener.onUpdateCLick(_region.getUuid(), name);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return _regions.size();
    }

    public class ListRegionViewHolder extends RecyclerView.ViewHolder {

        private Button btnSave, btnDelete;
        private EditText edtUpdate;
        private TextView txtRegion;

        public ListRegionViewHolder(View itemView) {
            super(itemView);
            txtRegion = itemView.findViewById(R.id.txtRegion);
            btnSave = itemView.findViewById(R.id.btnSave);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            edtUpdate = itemView.findViewById(R.id.edtUpdate);
        }
    }

    public interface UpdateClickListener {
        void onUpdateCLick(String uuid, String newName);
    }

    public interface DeleteClickListener {
        void onDeleteClick(String uuid);
    }
}
