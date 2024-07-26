package com.example.booking_restaurant.views.adapter_admins;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Category;

import java.util.List;
import java.util.Locale;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ListCategoryViewHolder> {

    private List<Category> _cates;
    private ListRegionAdapter.UpdateClickListener updateClickListener;
    private ListRegionAdapter.DeleteClickListener deleteClickListener;

    public void setUpdateClickListener(ListRegionAdapter.UpdateClickListener updateClickListener) {
        this.updateClickListener = updateClickListener;
    }

    public void setDeleteClickListener(ListRegionAdapter.DeleteClickListener deleteClickListener) {
        this.deleteClickListener = deleteClickListener;
    }
    public ListCategoryAdapter(List<Category> cates){
        this._cates = cates;
    }

    @NonNull
    @Override
    public ListCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category, parent, false);
        return new ListCategoryAdapter.ListCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoryViewHolder holder, int position) {
        Category _cate = _cates.get(position);

        holder.txtCate.setText(_cate.getName());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClickListener.onDeleteClick(_cate.getUuid());
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
                    holder.txtCate.setText(name.toUpperCase(Locale.ROOT));
                    updateClickListener.onUpdateCLick(_cate.getUuid(), name);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _cates.size();
    }

    public class ListCategoryViewHolder extends RecyclerView.ViewHolder {

        private Button btnSave, btnDelete;
        private EditText edtUpdate;
        private TextView txtCate;

        public ListCategoryViewHolder(View itemView){
            super(itemView);
            btnSave = itemView.findViewById(R.id.btnSave);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            edtUpdate = itemView.findViewById(R.id.edtUpdate);
            txtCate = itemView.findViewById(R.id.txtCate);
        }
    }

    public interface UpdateClickListener {
        void onUpdateCLick(String uuid, String newName);
    }

    public interface DeleteClickListener {
        void onDeleteClick(String uuid);
    }
}
