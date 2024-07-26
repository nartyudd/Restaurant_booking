package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.views.Helpers.OnUpdateRestaurantListener;
import com.example.booking_restaurant.views.adapter_admins.ListRestaurantAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListRestaurantFragment extends Fragment {

    private RecyclerView recyclerView;

    public ListRestaurantFragment() {

    }


    public static ListRestaurantFragment newInstance() {
        ListRestaurantFragment fragment = new ListRestaurantFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private RestaurantAdminRepository resRepo;
    private ListRestaurantAdapter listResAdapter;
    private List<Restaurant> _restaurant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);

        resRepo = new RestaurantAdminRepository();
        _restaurant = new ArrayList<>();
        Init(view);
        LoadData(view);
        return view;
    }

    private void Init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCategoryAdminFragment);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        listResAdapter = new ListRestaurantAdapter(_restaurant);

        recyclerView.setAdapter(listResAdapter);
    }

    private OnUpdateRestaurantListener updateRestaurantListener;

    public void setOnUpdateRestaurantListener(OnUpdateRestaurantListener listener) {
        this.updateRestaurantListener = listener;
    }


    private void LoadData(View view){
        resRepo.GetRestaurants(new BaseAdminRepository.OnDataFetchedListener<List<Restaurant>>() {
            @Override
            public void onDataFetched(List<Restaurant> _res) {
               _restaurant.addAll(_res);
               listResAdapter.notifyDataSetChanged();
            }
        });

        listResAdapter.setDeleteClickListener(new ListRestaurantAdapter.DeleteClickListener() {
            @Override
            public void onDeleteClick(String uuid) {
                Toast.makeText(getActivity(), "uuid : " + uuid, Toast.LENGTH_SHORT).show();
            }
        });


        HandleUpdateRestaurant(view);
    }

    public void HandleUpdateRestaurant(View view){
        listResAdapter.setUpdateClickListener(new ListRestaurantAdapter.UpdateClickListener() {
            @Override
            public void onUpdateClick(Restaurant res) {
                updateRestaurantListener.onUpdateRestaurant(res);
            }
        });
    }
}