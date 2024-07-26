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
import com.example.booking_restaurant.data.models.Table;
import com.example.booking_restaurant.data.repository_admins.BaseAdminRepository;
import com.example.booking_restaurant.data.repository_admins.RegionAdminRepository;
import com.example.booking_restaurant.data.repository_admins.TableAdminRepository;
import com.example.booking_restaurant.views.Helpers.ClickAddTableListener;
import com.example.booking_restaurant.views.adapter_admins.ListTableAdapter;
import com.example.booking_restaurant.views.adapter_admins.ListTableRegAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListTableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListTableFragment extends Fragment {


    private static final String ARG_PARAM2 = "nameRes";
    private static final String ARG_PARAM3 = "idRegion";
    private static final String ARG_PARAM4 = "nameRegion";

    private String nameRes;
    private String idRegion;
    private String nameRegion;

    public ListTableFragment() {
        // Required empty public constructor
    }

    public static ListTableFragment newInstance(String nameRes, String idRegion,
                                                String nameRegion) {
        ListTableFragment fragment = new ListTableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, nameRes);
        args.putString(ARG_PARAM3, idRegion);
        args.putString(ARG_PARAM4, nameRegion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nameRes = getArguments().getString(ARG_PARAM2);
            idRegion = getArguments().getString(ARG_PARAM3);
            nameRegion = getArguments().getString(ARG_PARAM4);
        }
    }
    private RecyclerView recyclerViewListTable;
    private TableAdminRepository tableRepo;
    private ListTableAdapter listTableAdapter;
    private List<Table> _tables;
    private GridLayoutManager gridLayoutManager;
    private FloatingActionButton floating_action_button;

    private ClickAddTableListener clickAddTableListener;

    public void setClickAddTableListener(ClickAddTableListener clickAddTableListener) {
        this.clickAddTableListener = clickAddTableListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_table, container, false);

        init(view);
        return view;
    }

    private void init(View view){
        recyclerViewListTable = view.findViewById(R.id.recyclerViewListTable);
        floating_action_button = view.findViewById(R.id.floating_action_button);
//        tableRepo = new TableAdminRepository();

        gridLayoutManager = new GridLayoutManager(view.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerViewListTable.setLayoutManager(gridLayoutManager);
        _tables = new ArrayList<>();
        setRecyclerViewRes();

        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddTableListener.ClickAddTable(idRegion, nameRegion);
            }
        });

    }

    private void setRecyclerViewRes(){
        listTableAdapter = new ListTableAdapter(_tables);

        recyclerViewListTable.setAdapter(listTableAdapter);
//        loadDataRes();


    }

//    private void loadDataRes(){
//        tableRepo.GetAllTable(idRegion, new BaseAdminRepository.OnDataFetchedListener<List<Table>>() {
//            @Override
//            public void onDataFetched(List<Table> tables) {
//                _tables.addAll(tables);
//
//                listTableAdapter.notifyDataSetChanged();
//            }
//        });
//    }
}