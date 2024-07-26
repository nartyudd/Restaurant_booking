package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Region;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RegionAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.views.Helpers.ClickRegionListener;
import com.example.booking_restaurant.views.Helpers.OnUpdateRestaurantListener;
import com.example.booking_restaurant.views.adapter_admins.ListRegionAdapter;
import com.example.booking_restaurant.views.adapter_admins.ListRegionResAdapter;
import com.example.booking_restaurant.views.adapter_admins.ListTableRegAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListTableRegFragment extends Fragment {

    private static final String ARG_PARAM1 = "restaurantId";

    // TODO: Rename and change types of parameters
    private String restaurantId;

    public ListTableRegFragment() {
        // Required empty public constructor
    }

    public static ListTableRegFragment newInstance(String restaurantId) {
        ListTableRegFragment fragment = new ListTableRegFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, restaurantId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantId = getArguments().getString(ARG_PARAM1);
        }
    }

    private ListTableRegAdapter regAdapter;
    private RegionAdminRepository regRepo;
    private List<Region> _regions;
    private RecyclerView recyclerViewListTable;
    private GridLayoutManager gridLayoutManager;
    private ClickRegionListener clickRegionListener;

    public void setClickRegionListener(ClickRegionListener clickRegionListener) {
        this.clickRegionListener = clickRegionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_table_reg, container, false);

        init(view);
        return view;
    }


    private void init(View view){
        recyclerViewListTable = view.findViewById(R.id.recyclerViewListTableReg);
        regRepo = new RegionAdminRepository();
        gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerViewListTable.setLayoutManager(gridLayoutManager);
        _regions = new ArrayList<>();
        setRecyclerViewRes();
    }

    private void loadDataRes(){
        regRepo.GetRegions(restaurantId, new BaseAdminRepository.OnDataFetchedListener<List<Region>>() {
            @Override
            public void onDataFetched(List<Region> regions) {
                regions.sort(Comparator.comparing(Region::getName));

                _regions.addAll(regions);

                regAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setRecyclerViewRes(){
        regAdapter = new ListTableRegAdapter(_regions);

        recyclerViewListTable.setAdapter(regAdapter);
        loadDataRes();

        regAdapter.setClickListener(new ListTableRegAdapter.ClickListener() {
            @Override
            public void onClick(String regionId, String regionName) {
                clickRegionListener.ClickRegion(restaurantId, regionId, regionName);
            }
        });
    }
}