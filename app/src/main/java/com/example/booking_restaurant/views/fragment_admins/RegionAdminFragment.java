package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Region;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RegionAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.utilities.GenID;
import com.example.booking_restaurant.views.adapter_admins.ListRegionAdapter;
import com.example.booking_restaurant.views.adapter_admins.ListRegionResAdapter;
import com.example.booking_restaurant.views.adapter_admins.SpinnerResAdapter;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RegionAdminFragment extends Fragment {

    private static final String TAG = "REGION_FRAGMENT";


    public RegionAdminFragment() {

    }

    public static RegionAdminFragment newInstance() {
        RegionAdminFragment fragment = new RegionAdminFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private TabLayout tab_region;
    private RecyclerView recyclerListRestaurant, recyclerListRegion;
    private MaterialCardView addRegion;
    private Spinner spinner1;
    private EditText txtNameRegion;
    private TextView txtNameRes;
    private String uuid;
    private Button btnAddRegion;
    private List<Restaurant> _res = new ArrayList<>();
    private  ListRegionResAdapter listRegionResAdapter;
    private ListRegionAdapter listRegionAdapter;
    private RegionAdminRepository regionRepo;
    private List<Region> _regions = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_region_admin, container, false);

        SetInitTab(view);
        InitTab();
        setSpinner(view);
        LoadDataRes();
        HandleRecyclerViewListRestaurant(view);
        return view;
    }

    private void SetInitTab(View view){
        regionRepo = new RegionAdminRepository();
        tab_region = view.findViewById(R.id.tab_region);
        recyclerListRestaurant = view.findViewById(R.id.recyclerListRestaurant);
        recyclerListRegion = view.findViewById(R.id.recyclerListRegion);
        addRegion = view.findViewById(R.id.addRegion);
        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        txtNameRes = (TextView) view.findViewById(R.id.txtNameRes);
        txtNameRegion = (EditText) view.findViewById(R.id.txtNameRegion);
        btnAddRegion = (Button) view.findViewById(R.id.btnAddRegion);
        tab_region.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedPosition = tab.getPosition();
                switch (selectedPosition) {
                    case 0:
                        InitTab();
                        break;
                    case 1:
                        recyclerListRestaurant.setVisibility(View.GONE);
                        recyclerListRegion.setVisibility(View.GONE);
                        addRegion.setVisibility(View.VISIBLE);
                        _regions.clear();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnAddRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnAddRegion();
            }
        });

    }

    private void HandleRecyclerViewListRestaurant(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);

        recyclerListRestaurant.setLayoutManager(gridLayoutManager);

        listRegionResAdapter = new ListRegionResAdapter(_res);

        recyclerListRestaurant.setAdapter(listRegionResAdapter);

        listRegionResAdapter.setReadClickListener(new ListRegionResAdapter.ReadClickListener() {
            @Override
            public void onReadClick(String idRestaurant) {
                HandleRecyclerViewListRegion(view);
                recyclerListRegion.setVisibility(View.VISIBLE);
                recyclerListRestaurant.setVisibility(View.GONE);
                LoadDataRegion(idRestaurant);
            }
        });
    }

    private void LoadDataRegion(String idRestaurant){
        regionRepo.GetRegions(idRestaurant, new BaseAdminRepository.OnDataFetchedListener<List<Region>>() {

            @Override
            public void onDataFetched(List<Region> regions) {
                regions.sort(Comparator.comparing(Region::getName));

                _regions.addAll(regions);

                listRegionAdapter.notifyDataSetChanged();
            }
        });
    }

    private void HandleRecyclerViewListRegion(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);

        recyclerListRegion.setLayoutManager(gridLayoutManager);

        listRegionAdapter = new ListRegionAdapter(_regions);

        recyclerListRegion.setAdapter(listRegionAdapter);

        listRegionAdapter.setUpdateClickListener(new ListRegionAdapter.UpdateClickListener() {
            @Override
            public void onUpdateCLick(String uuid, String newName) {
                regionRepo.UpdateRegion(uuid, newName);
                Toast.makeText(getActivity(), "Bạn đã sửa thành công.!", Toast.LENGTH_SHORT).show();
            }
        });


        listRegionAdapter.setDeleteClickListener(new ListRegionAdapter.DeleteClickListener() {
            @Override
            public void onDeleteClick(String uuid) {
                regionRepo.DeleteRegion(uuid);
                DeleteRegion(uuid);
                Toast.makeText(getActivity(), "Bạn đã xóa thành công.!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DeleteRegion(String uuid){
       _regions.removeIf(region -> region.getUuid().equals(uuid));

       listRegionAdapter.notifyDataSetChanged();
    }

    private void setSpinner(View view){
        SpinnerResAdapter spinnerResAdapter = new SpinnerResAdapter(getActivity().getApplicationContext(), _res);
        spinner1.setAdapter(spinnerResAdapter);
        spinnerResAdapter.setDropDownResource(android.R.layout.select_dialog_singlechoice);
        spinnerResAdapter.setClickSpinnerListener(new SpinnerResAdapter.ClickSpinnerListener() {
            @Override
            public void onClickSpinner(String uid, String name) {
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                uuid = uid;
                txtNameRes.setText(name);
            }
        });
    }

    private void LoadDataRes(){
        RestaurantAdminRepository resRepo = new RestaurantAdminRepository();
        resRepo.GetRestaurants(new BaseAdminRepository.OnDataFetchedListener<List<Restaurant>>() {
            @Override
            public void onDataFetched(List<Restaurant> data) {
                _res.addAll(data);

                listRegionResAdapter.notifyDataSetChanged();
            }
        });
    }

    private void InitTab(){
        recyclerListRestaurant.setVisibility(View.VISIBLE);
        recyclerListRegion.setVisibility(View.GONE);
        addRegion.setVisibility(View.GONE);
    }

    private void BtnAddRegion(){
        String nameRes, nameRegion;

        nameRes = txtNameRes.getText().toString().trim();
        nameRegion = txtNameRegion.getText().toString().trim();

        if(nameRegion == null || nameRegion.equals("") || nameRes.equals("") || nameRes == null){
            Toast.makeText(getActivity(), "Bạn phải điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
        } else {
            RegionAdminRepository regionRepo = new RegionAdminRepository();

            Region region = new Region(GenID.genUUID(), nameRegion.toUpperCase(Locale.ROOT), uuid);

            regionRepo.AddRegion(region);
            Toast.makeText(getActivity(), "Bạn đã thêm thành công.!", Toast.LENGTH_SHORT).show();

        }

    }
}