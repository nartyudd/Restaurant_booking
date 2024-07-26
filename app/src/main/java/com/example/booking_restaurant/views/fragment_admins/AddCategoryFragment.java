package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Category;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.CategoryAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.utilities.GenID;
import com.example.booking_restaurant.views.adapter_admins.SpinnerResAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddCategoryFragment extends Fragment {

    public AddCategoryFragment() {

    }

    public static AddCategoryFragment newInstance() {
        AddCategoryFragment fragment = new AddCategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private MaterialButton btnAddCategory;
    private EditText edtNameCategory;
    private Spinner spinner1;
    private TextView txtNameRes;
    private RestaurantAdminRepository resRepos;
    private List<Restaurant> _res;
    private CategoryAdminRepository cateRepo;
    private String uuid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        init(view);
        setSpinner1();
        return view;
    }

    private void init(View view){
        btnAddCategory = view.findViewById(R.id.btnAddCategory);
        edtNameCategory = view.findViewById(R.id.edtNameCategory);
        spinner1 = view.findViewById(R.id.spinner1);
        txtNameRes = view.findViewById(R.id.txtNameRes);
        resRepos = new RestaurantAdminRepository();
        cateRepo = new CategoryAdminRepository();

        _res = new ArrayList<>();
        LoadDateRes();
        btnAddCategory();
    }

    private void LoadDateRes(){
        resRepos.GetRestaurants(new BaseAdminRepository.OnDataFetchedListener<List<Restaurant>>() {
            @Override
            public void onDataFetched(List<Restaurant> res) {
                _res.addAll(res);
            }
        });
    }

    private void setSpinner1(){
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

    private void btnAddCategory(){
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameCategory.getText().toString().trim();
                String nameRes = txtNameRes.getText().toString().trim();
                if(name.equals("") || name.isEmpty() || name == null || nameRes.equals("") || nameRes == null){
                    Toast.makeText(getActivity(), "Bạn phải điền đầy đủ các trường.!", Toast.LENGTH_SHORT).show();
                } else {
                    Category cate = new Category(GenID.genUUID(), name.toUpperCase(Locale.ROOT),  uuid);
                    cateRepo.AddCategory(cate);
                    edtNameCategory.setText(null);
                    txtNameRes.setText(null);
                    Toast.makeText(getActivity(), "Bạn đã thêm thành công.!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}