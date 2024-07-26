package com.example.booking_restaurant.views.adapter_admins;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_restaurant.R;
import com.example.booking_restaurant.viewmodels.UserViewModel;

import java.util.List;

public class UserAdminAdapter extends RecyclerView.Adapter<UserAdminAdapter.UserAdminViewHolder> {


    private List<UserViewModel> _userList;
    private OnRoleChangedListener onRoleChangedListener;
    private OnExistsChangedListener onExistsChangedListener;
    public UserAdminAdapter(List<UserViewModel> userList){
        this._userList = userList;
    }

    public void setOnRoleChangedListener(OnRoleChangedListener onRoleChangedListener){
        this.onRoleChangedListener = onRoleChangedListener;
    }
    public void setOnExistsChangedListener(OnExistsChangedListener onExistsChangedListener){
        this.onExistsChangedListener = onExistsChangedListener;
    }

    @NonNull
    @Override
    public UserAdminAdapter.UserAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_admin, parent, false);
        return new  UserAdminAdapter.UserAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdminViewHolder holder, int position) {
        UserViewModel users = _userList.get(position);

        holder.userId.setText(users.getUserId());
        holder.userName.setText(users.getUserName());
        holder.email.setText(users.getEmail());


        // set the cureent state of the radio button assign user role
        if(users.getRole().equals("user")){
            holder.radio_button_user.setChecked(true);
        } else {
            holder.radio_button_admin.setChecked(true);
        }

        if(users.isExists()){
            holder.radio_button_active.setChecked(true);
        } else{
            holder.radio_button_non_active.setChecked(true);
        }


        holder.radio_group_role.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = radioGroup.findViewById(checkedId);

                String newRole = "";
                if (checkedRadioButton.getId() == R.id.radio_button_user) {
                    newRole = "user";
                } else if (checkedRadioButton.getId() == R.id.radio_button_admin) {
                    newRole = "admin";
                }

                if (checkedRadioButton == null)
                    return;

                onRoleChangedListener.onRoleChanged(users.getUserId(), newRole, users.getRole());
            }
        });

        holder.radio_group_exists.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = radioGroup.findViewById(i);

                boolean newExists;
                if(checkedRadioButton.getId() == R.id.radio_button_active){
                    newExists = true;
                } else {
                    newExists = false;
                }

                onExistsChangedListener.onExistsChanged(users.getUserId(), newExists);
            }
        });

    }

    @Override
    public int getItemCount() {
        return _userList.size();
    }

    public class UserAdminViewHolder extends RecyclerView.ViewHolder {

        private TextView userId;
        private TextView userName;
        private TextView email;
        private RadioGroup radio_group_role;
        private RadioGroup radio_group_exists;
        private RadioButton radio_button_admin;
        private RadioButton radio_button_user;
        private RadioButton radio_button_active;
        private RadioButton radio_button_non_active;

        public UserAdminViewHolder(View item){
            super(item);
            userId = item.findViewById(R.id.txtUserID);
            userName = item.findViewById(R.id.txtUserName);
            email = item.findViewById(R.id.txtEmail);
            radio_group_role = item.findViewById(R.id.radio_group_role);
            radio_group_exists = item.findViewById(R.id.radio_group_exists);
            radio_button_admin = item.findViewById(R.id.radio_button_admin);
            radio_button_user = item.findViewById(R.id.radio_button_user);
            radio_button_active = item.findViewById(R.id.radio_button_active);
            radio_button_non_active = item.findViewById(R.id.radio_button_non_active);
        }
    }

    public interface OnRoleChangedListener {
        void onRoleChanged(String userId, String newRole, String oldRole);
    }

    public interface OnExistsChangedListener {
        void onExistsChanged(String userId, boolean newExists);
    }
}
