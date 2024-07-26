package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.views.Helpers.OnUpdateRestaurantListener;
import com.google.android.material.tabs.TabLayout;

public class RestaurantAdminFragment extends Fragment implements OnUpdateRestaurantListener {
    private FragmentManager fragmentManager;
    private TabLayout tablayou_restaurant;

    public RestaurantAdminFragment() {

    }

    public static RestaurantAdminFragment newInstance() {
        RestaurantAdminFragment fragment = new RestaurantAdminFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_admin, container, false);
        fragmentManager = getChildFragmentManager();

        tablayou_restaurant = view.findViewById(R.id.tablayou_restaurant);

        handleListRestaurant();
        InitTabLayout(tablayou_restaurant);

        return view;
    }

    public void InitTabLayout(TabLayout tablayou_restaurant) {
        tablayou_restaurant.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedPosition = tab.getPosition();
                switch (selectedPosition) {
                    case 0:
                        handleListRestaurant();
                        break;
                    case 1:
                        handleCURestaurant();
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
    }


    public void handleListRestaurant() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListRestaurantFragment frag = ListRestaurantFragment.newInstance();
        frag.setOnUpdateRestaurantListener(this);
        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("list_restaurant_admin_fragment");
        fragmentTransaction.commit();
    }

    public void handleCURestaurant() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CRestaurantFragment frag = CRestaurantFragment.newInstance();

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("c_restaurant_admin_fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onUpdateRestaurant(Restaurant res) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        URestaurantFragment frag = URestaurantFragment.newInstance(res.getUuid(), res.getName(), res.getAddress(), res.getUriImage());
        frag.setOnUpdateRestaurantListener(this);
        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("u_restaurant_admin_fragment");
        fragmentTransaction.commit( );
    }

    @Override
    public void onUpdateRestaurantClick(String uuid, String name, String address, String uriImage) {
        handleListRestaurant();
    }
}