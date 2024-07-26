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
import com.example.booking_restaurant.data.models.Category;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.CategoryAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.views.adapter_admins.ListCategoryAdapter;
import com.example.booking_restaurant.views.adapter_admins.ListRegionAdapter;
import com.example.booking_restaurant.views.adapter_admins.ListRegionResAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListCategoryResFragment extends Fragment {


    public ListCategoryResFragment() {
    }


    public static ListCategoryResFragment newInstance() {
        ListCategoryResFragment fragment = new ListCategoryResFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewList;
    private ListRegionResAdapter resAdapter;
    private RestaurantAdminRepository resRepo;
    private List<Restaurant> _res;
    private ListCategoryAdapter categoryAdapter;
    private CategoryAdminRepository cateRepo;
    private List<Category> _cates;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_category_res, container, false);

        init(view);
        return view;
    }

    private void init(View view){
        recyclerViewList = view.findViewById(R.id.recyclerViewList);
        resRepo = new RestaurantAdminRepository();
        gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(gridLayoutManager);
        _res = new ArrayList<>();
        _cates = new ArrayList<>();
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

    private void setRecyclerViewRes(){
        resAdapter = new ListRegionResAdapter(_res);

        recyclerViewList.setAdapter(resAdapter);
        loadDataRes();

        resAdapter.setReadClickListener(new ListRegionResAdapter.ReadClickListener() {
            @Override
            public void onReadClick(String idRestaurant) {
                recyclerViewList.setAdapter(null);
                setRecyclerViewCate(idRestaurant);
            }
        });
    }

    private void setRecyclerViewCate(String idRestaurant){

        categoryAdapter = new ListCategoryAdapter(_cates);
        recyclerViewList.setAdapter(categoryAdapter);
        LoadDataCate(idRestaurant);

        categoryAdapter.setDeleteClickListener(new ListRegionAdapter.DeleteClickListener() {
            @Override
            public void onDeleteClick(String uuid) {
                cateRepo.DeleteCate(uuid);
                DeleteCate(uuid);
                Toast.makeText(getActivity(), "Bạn đã xóa thành công.!", Toast.LENGTH_SHORT).show();
            }
        });

        categoryAdapter.setUpdateClickListener(new ListRegionAdapter.UpdateClickListener() {
            @Override
            public void onUpdateCLick(String uuid, String newName) {
                cateRepo.UpdateCate(uuid, newName.toUpperCase(Locale.ROOT));
                Toast.makeText(getActivity(), "Bạn đã sửa thành công.!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DeleteCate(String uuid){
        _cates.removeIf(region -> region.getUuid().equals(uuid));

        categoryAdapter.notifyDataSetChanged();
    }

    private void LoadDataCate(String idRestaurant){
        cateRepo = new CategoryAdminRepository();

        cateRepo.GetCategoy(idRestaurant, new BaseAdminRepository.OnDataFetchedListener<List<Category>>() {
            @Override
            public void onDataFetched(List<Category> cates) {
                _cates.addAll(cates);

                categoryAdapter.notifyDataSetChanged();

            }
        });
    }
}