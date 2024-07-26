package com.example.booking_restaurant.views.fragment_admins;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.booking_restaurant.R;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatergoryAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatergoryAdminFragment extends Fragment {

    private static final String TAG = "CATEGORY_ADMIN_FRAGMENT";
    public CatergoryAdminFragment() {

    }


    public static CatergoryAdminFragment newInstance() {
        CatergoryAdminFragment fragment = new CatergoryAdminFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private FragmentManager fragmentManager;
    private MaterialToolbar topAppBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_catergory_admin, container, false);

        init(view);

        return view;
    }

    private void init(View view){
        fragmentManager = getChildFragmentManager();
        topAppBar = view.findViewById(R.id.topAppBar);
        handleListCatory();
        setAppBar();
    }

    private void handleListCatory(){

        ListCategoryResFragment frag = ListCategoryResFragment.newInstance();

        replaceFragment(frag);
    }

    private void setAppBar(){
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.add) {
                    AddCategoryFragment frag = AddCategoryFragment.newInstance();
                    replaceFragment(frag);
                }
                return false;
            }
        });

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleListCatory();
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentCategoryAdmin, fragment);
        fragmentTransaction.addToBackStack("add_category_admin_fragment");
        fragmentTransaction.commit();
    }
}