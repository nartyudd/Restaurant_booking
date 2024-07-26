package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.views.Helpers.ClickAddTableListener;
import com.example.booking_restaurant.views.Helpers.ClickRegionListener;
import com.example.booking_restaurant.views.Helpers.ClickRestaurantListener;
import com.example.booking_restaurant.views.adapter_admins.ListTableAdapter;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableAdminFragment extends Fragment implements ClickRestaurantListener, ClickRegionListener, ClickAddTableListener {

    public TableAdminFragment() {

    }

    public static TableAdminFragment newInstance() {
        TableAdminFragment fragment = new TableAdminFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentManager fragmentManager;
    private MaterialToolbar topAppBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_admin, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        fragmentManager = getChildFragmentManager();
        topAppBar = view.findViewById(R.id.topAppBar);
        handleListTable();
        setAppBar();
    }

    private void handleListTable() {

        ListTableResFragment frag = ListTableResFragment.newInstance();
        frag.setClickRegionListener(this);
        replaceFragment(frag);
    }

    private void setAppBar() {
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleListTable();
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentCategoryAdmin, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickRestaurant(String restaurantId) {
        ListTableRegFragment fragment = ListTableRegFragment.newInstance(restaurantId);
        fragment.setClickRegionListener(this);
        replaceFragment(fragment);
    }

    @Override
    public void ClickRegion(String idRes, String idRegion, String nameRegion) {
        ListTableFragment frag = ListTableFragment.newInstance(idRes, idRegion, nameRegion);
        frag.setClickAddTableListener(this);
        replaceFragment(frag);
    }

    @Override
    public void ClickAddTable(String idRegion, String nameRegion) {
        AddTableFragment frag = AddTableFragment.newInstance(idRegion, nameRegion);
        replaceFragment(frag);
    }
}