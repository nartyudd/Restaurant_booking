package com.example.booking_restaurant.views.fragment_users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.HomeHorModel;
import com.example.booking_restaurant.data.models.HomeVerModel;
import com.example.booking_restaurant.views.adapter_users.HomeHorAdapter;
import com.example.booking_restaurant.views.adapter_users.HomeVerAdapter;
import com.example.booking_restaurant.views.adapter_users.UpdateVerticalRec;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements UpdateVerticalRec{
    RecyclerView homeHorizontalRec, homeVerticalRec;
    /// Horizontalllllllll
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;

    /// Verticallllllll

    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstancesState){
        View root = inflater.inflate(R.layout.fragment_home, container,false);

        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        ///verticalRec
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);

        ////////// Horizontal RecyclerView
        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.bbq, "BBQ"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hotpot, "Lẩu"));
        homeHorModelList.add(new HomeHorModel(R.drawable.monviet, "Món Việt"));
        homeHorModelList.add(new HomeHorModel(R.drawable.vegetable, "Món chay"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sushi, "Món Nhật"));


        homeHorAdapter = new HomeHorAdapter( this,getActivity(),homeHorModelList);

        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);



        ////////// Vertical RecyclerView
        homeVerModelList = new ArrayList<>();

        homeVerModelList.add(new HomeVerModel(R.drawable.bbq, "BBQ","10:00 - 22:00","4.5","200K - 300K"));
        homeVerModelList.add(new HomeVerModel(R.drawable.bbq, "BBQ","10:00 - 22:00","4.5","200K - 300K"));
        homeVerModelList.add(new HomeVerModel(R.drawable.bbq, "BBQ","10:00 - 22:00","4.5","200K - 300K"));

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);

        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
//        homeVerticalRec.setHasFixedSize(true);
//        homeVerticalRec.setNestedScrollingEnabled(false);
        return root;
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        homeVerAdapter = new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}