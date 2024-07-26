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
import com.example.booking_restaurant.data.models.Region;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.models.Table;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RegionAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.data.repository_admins.TableAdminRepository;
import com.example.booking_restaurant.utilities.GenID;
import com.example.booking_restaurant.views.adapter_admins.SpinnerRegionAdapter;
import com.example.booking_restaurant.views.adapter_admins.SpinnerResAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddTableFragment extends Fragment {

    private static final String ARG_PARAM3 = "idRegion";
    private static final String ARG_PARAM4 = "nameRegion";

    private String idRegion;
    private String nameRegion;
    public AddTableFragment() {
        // Required empty public constructor
    }

    private MaterialButton btnAddTable;
    private EditText edtNameTable;
    private TextView txtNameRes;
    private TextView txtNameRegion, txtNameCate;

    public static AddTableFragment newInstance(String idRegion, String nameRegion) {
        AddTableFragment fragment = new AddTableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM3, idRegion);
        args.putString(ARG_PARAM4, nameRegion);
        fragment.setArguments(args);
        return fragment;
    }


    private TableAdminRepository tableRepo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idRegion = getArguments().getString(ARG_PARAM3);
            nameRegion = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_table, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        btnAddTable = view.findViewById(R.id.btnAddCategory);
        edtNameTable = view.findViewById(R.id.edtNameCategory);
        txtNameRes = view.findViewById(R.id.txtNameRes);
        txtNameRegion = view.findViewById(R.id.txtNameRegion);
        txtNameCate = view.findViewById(R.id.txtNameCate);

//        tableRepo = new TableAdminRepository();
//        txtNameRes.setText(nameRes);
        txtNameRegion.setText(nameRegion);
//        txtNameCate.setText(nameCate);
//        btnAddTable();
    }

    private void btnAddTable(){
        btnAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameTable.getText().toString().trim();
                if(name.equals("") || name.isEmpty() || name == null){
                    Toast.makeText(getActivity(), "Bạn phải điền đầy đủ các trường.!", Toast.LENGTH_SHORT).show();
                } else {
//                    Table table = new Table(GenID.genUUID(), name.toUpperCase(Locale.ROOT), idCate, idRegion);
//                    tableRepo.AddTable(table);
                    edtNameTable.setText(null);
                    Toast.makeText(getActivity(), "Bạn đã thêm thành công.!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}