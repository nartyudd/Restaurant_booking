package com.example.booking_restaurant.views.adapter_admins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Table;

import java.util.List;

public class ListTableAdapter extends RecyclerView.Adapter<ListTableAdapter.ListTableViewHolder> {

    private List<Table> _tables;

    public ListTableAdapter(List<Table> tables){
        this._tables = tables;
    }

    @NonNull
    @Override
    public ListTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_admin, parent, false);
        return new ListTableAdapter.ListTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTableViewHolder holder, int position) {
        Table _table = _tables.get(position);

        holder.txtTable.setText(_table.getName());
    }

    @Override
    public int getItemCount() {
        return _tables.size();
    }

    public class ListTableViewHolder extends RecyclerView.ViewHolder {
        private Button btnSave, btnDelete;
        private EditText edtUpdate;
        private TextView txtTable;

        public ListTableViewHolder(View itemView){
            super(itemView);
            btnSave = itemView.findViewById(R.id.btnSave);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            edtUpdate = itemView.findViewById(R.id.edtUpdate);
            txtTable = itemView.findViewById(R.id.txtTable);
        }
    }
}
