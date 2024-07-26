package com.example.booking_restaurant.views.fragment_admins;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.UserAdminRepository;
import com.example.booking_restaurant.viewmodels.UserViewModel;
import com.example.booking_restaurant.views.adapter_admins.UserAdminAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserAdminFragment extends Fragment {

//    private AdminUserRepository adminUserRepository;
    private static final String TAG = "USER_ADMIN_FRAGMENT";
    private UserAdminAdapter userAdminAdapter;
    private RecyclerView recyclerView;
    private List<UserViewModel> newUserList;
    private  UserAdminRepository userAdminRepo;

    private static final String ARG_PARAM1 = "userId";
    private static final String ARG_PARAM2 = "role";


    private String userId;
    private String role;

    public UserAdminFragment() {
        // Required empty public constructor
    }

    public static UserAdminFragment newInstance(String userId, String role) {
        UserAdminFragment fragment = new UserAdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userId);
        args.putString(ARG_PARAM2, role);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_PARAM1);
            role = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_admin, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewContainerAdmin);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        userAdminRepo = new UserAdminRepository();

        newUserList = new ArrayList<>();

        userAdminAdapter = new UserAdminAdapter(newUserList);

        recyclerView.setAdapter(userAdminAdapter);

        loadData(view);

        return view;
    }

    public void loadData(View view){
        userAdminRepo.getListUser(new BaseAdminRepository.OnDataFetchedListener<List<UserViewModel>>() {
            @Override
            public void onDataFetched(List<UserViewModel> users) {
                users.forEach(user -> {
                    newUserList.add(user);
                });
                userAdminAdapter.notifyDataSetChanged();
            }
        });

        userAdminAdapter.setOnRoleChangedListener(new UserAdminAdapter.OnRoleChangedListener() {
            @Override
            public void onRoleChanged(String userId, String newRole, String oldRole) {
                if(userAdminRepo.ChangeRole(userId, newRole)){
                    UpdateRole(newUserList, userId, newRole);
                    Toast.makeText(view.getContext(), "Thay đổi vai trò thành công.! " + oldRole + " -> " + newRole, Toast.LENGTH_SHORT).show();
                    userAdminAdapter.notifyDataSetChanged();
                }
            }
        });

        userAdminAdapter.setOnExistsChangedListener(new UserAdminAdapter.OnExistsChangedListener() {
            @Override
            public void onExistsChanged(String userId, boolean newExists) {
                if(userAdminRepo.ChangeExists(userId, newExists)){
                    UpdateExists(newUserList, userId, newExists);
                    Toast.makeText(view.getContext(), "Thay đổi trạng thái hoạt động của user thành công.! ", Toast.LENGTH_SHORT).show();
                    userAdminAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    public void UpdateRole(List<UserViewModel> users, String userId, String newRole){
        users.forEach(user -> {
            if(user.getUserId().equals(userId)){
                user.setRole(newRole);
            }
        });
    }

    public void UpdateExists(List<UserViewModel> users, String userId, boolean newExists){
        users.forEach(user -> {
            if(user.getUserId().equals(userId)){
                user.setExists(newExists);
            }
        });
    }
}