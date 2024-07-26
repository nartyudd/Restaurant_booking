package com.example.booking_restaurant.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.UserSingleton;
import com.example.booking_restaurant.views.fragment_admins.BillAdminFragment;
import com.example.booking_restaurant.views.fragment_admins.CatergoryAdminFragment;
import com.example.booking_restaurant.views.fragment_admins.RegionAdminFragment;
import com.example.booking_restaurant.views.fragment_admins.RestaurantAdminFragment;
import com.example.booking_restaurant.views.fragment_admins.TableAdminFragment;
import com.example.booking_restaurant.views.fragment_admins.UserAdminFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


public class AdminActivity extends AppCompatActivity {
    private static final String TAG = "AdminActivity";
    private MaterialToolbar topAppBar;
    private ShapeableImageView shapeableImageView;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    private static final int NAV_USER_ADMIN = R.id.navUserAdmin;
    private static final int NAV_CATEGORY_ADMIN = R.id.navCategoryAdmin;
    private static final int NAV_TABLE_ADMIN = R.id.navTableAdmin;
    private static final int NAV_BILL_ADMIN = R.id.navBillAdmin;
    private static final int NAV_LOGOUT_ADMIN = R.id.navLogoutAdmin;
    private static final int NAV_REIOGN_ADMIN = R.id.navRegionAdmin;
    private static final int NAV_RESTAURANT_ADMIN = R.id.navRestaurantAdmin;

    private String userId = UserSingleton.getInstance().getUserID();
    private String role = UserSingleton.getInstance().getRole();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FirebaseApp.initializeApp(AdminActivity.this);
        navigationView = (NavigationView) findViewById(R.id.navigationMenuAdmin);
        topAppBar = (MaterialToolbar) findViewById(R.id.topAppBarAdmin);
        mAuth = FirebaseAuth.getInstance();


        fragmentManager = getSupportFragmentManager();

        setSupportActionBar(topAppBar);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                navigationView.setVisibility(View.VISIBLE);
            }
        });

        setFragmentInit();
        handleCloseNavigation();
        setListenerItemNavigation();
    }

    private void setFragmentInit(){
        handleUserAdminFragment();
    }

    private void handleUserAdminFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        UserAdminFragment frag = UserAdminFragment.newInstance(userId, role);

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("user_admin_fragment");
        fragmentTransaction.commit();
    }


    private void handleCategoryAdminFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CatergoryAdminFragment frag = CatergoryAdminFragment.newInstance();

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("category_admin_fragment");
        fragmentTransaction.commit();
    }

    private void handleTableAdminFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TableAdminFragment frag = TableAdminFragment.newInstance();

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("table_admin_fragment");
        fragmentTransaction.commit();
    }

    private void handleBillAdminFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BillAdminFragment frag = BillAdminFragment.newInstance(userId, role);

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("bill_admin_fragment");
        fragmentTransaction.commit();
    }

    private void handleRegionAdminFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RegionAdminFragment frag = RegionAdminFragment.newInstance();

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("region_admin_fragment");
        fragmentTransaction.commit();
    }

    private void handleRestaurantAdminFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RestaurantAdminFragment frag = RestaurantAdminFragment.newInstance();

        fragmentTransaction.replace(R.id.fragmentContainerAdmin, frag);
        fragmentTransaction.addToBackStack("restaurant_admin_fragment");
        fragmentTransaction.commit();
    }

    public void setListenerItemNavigation()
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if(itemId == NAV_USER_ADMIN){
                    handleUserAdminFragment();
                } else if(itemId == NAV_RESTAURANT_ADMIN){
                    handleRestaurantAdminFragment();
                } else if (itemId == NAV_CATEGORY_ADMIN) {
                    handleCategoryAdminFragment();
                } else if(itemId == NAV_REIOGN_ADMIN) {
                    handleRegionAdminFragment();
                } else if (itemId == NAV_TABLE_ADMIN) {
                    handleTableAdminFragment();
                } else if (itemId == NAV_BILL_ADMIN){
                    handleBillAdminFragment();
                } else if (itemId == NAV_LOGOUT_ADMIN) {
                    handleNavLogout();
                } else {
                    setCLoseNavigtion();
                }

                setCLoseNavigtion();

                return true;
            }
        });
    }

    public void handleCloseNavigation(){

        shapeableImageView = (ShapeableImageView) findViewById(R.id.navigationCloseAdmin);


        shapeableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setCLoseNavigtion();
            }
        });
    }


    public void setCLoseNavigtion(){
        navigationView.setVisibility(View.GONE);
    }

    private void handleNavLogout() {
        mAuth.signOut();
        startActivity(new Intent(AdminActivity.this, SignInActivity.class));
        finish();
    }

}