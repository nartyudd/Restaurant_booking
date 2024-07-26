package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.views.Helpers.ClickRestaurantListener;
import com.example.booking_restaurant.views.adapter_admins.ListRegionResAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListTableResFragment extends Fragment {

    public ListTableResFragment() {
    }


    public static ListTableResFragment newInstance() {
        ListTableResFragment fragment = new ListTableResFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private ListRegionResAdapter resAdapter;
    private RestaurantAdminRepository resRepo;
    private List<Restaurant> _res;
    private RecyclerView recyclerViewListTable;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_table_res, container, false);

        init(view);
        return view;
    }

    private void init(View view){
        recyclerViewListTable = view.findViewById(R.id.recyclerViewListTable);
        resRepo = new RestaurantAdminRepository();
        gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerViewListTable.setLayoutManager(gridLayoutManager);
        _res = new ArrayList<>();
        setRecyclerViewRes();
    }

    private void loadDataRes(){
        resRepo.GetRestaurants(new BaseAdminRepository.OnDataFetchedListener<List<Restaurant>>() {
            @Override
            public void onDataFetched(List<Restaurant> res) {
                _res.addAll(res);

                resAdapter.notifyDataSetChanged();
            }
        });
    }

    private ClickRestaurantListener clickRegionListener;

    public void setClickRegionListener(ClickRestaurantListener clickRegionListener) {
        this.clickRegionListener = clickRegionListener;
    }

    private void setRecyclerViewRes(){
        resAdapter = new ListRegionResAdapter(_res);

        recyclerViewListTable.setAdapter(resAdapter);
        loadDataRes();

        resAdapter.setReadClickListener(new ListRegionResAdapter.ReadClickListener() {
            @Override
            public void onReadClick(String idRestaurant) {
                clickRegionListener.onClickRestaurant(idRestaurant);
            }
        });
    }
}