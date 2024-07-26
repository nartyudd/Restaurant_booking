package com.example.booking_restaurant.views.fragment_users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.ChooseTableModel;
import com.example.booking_restaurant.views.adapter_users.ChooseTableAdapter;



import java.util.ArrayList;
import java.util.List;

public class ChooseTableFragment extends Fragment {
    RecyclerView recyclerView;
    List<ChooseTableModel> chooseTableModels;
    ChooseTableAdapter chooseTableAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_choose_table, container, false);

        recyclerView = root.findViewById(R.id.choose_table_rec);

        GridLayoutManager grid = new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(grid);
//
        chooseTableModels = new ArrayList<>();
        chooseTableModels.add(new ChooseTableModel(R.drawable.table,"Bàn đôi","Còn bàn","Phù hợp cho các cặp đôi" ));
        chooseTableModels.add(new ChooseTableModel(R.drawable.table,"Bàn gia đình","Còn bàn","Phù hợp cho gia đình 4 người" ));
        chooseTableModels.add(new ChooseTableModel(R.drawable.table,"Bàn nhóm nhỏ","Còn bàn","Phù hợp cho nhóm từ 6 - 8 người" ));
        chooseTableModels.add(new ChooseTableModel(R.drawable.table,"Bàn nhóm lớn","Còn bàn","Phù hợp cho nhóm từ 10 - 20 người"));

        chooseTableAdapter = new ChooseTableAdapter(chooseTableModels);
        recyclerView.setAdapter(chooseTableAdapter);
        chooseTableAdapter.notifyDataSetChanged();

        return root;
    }
}