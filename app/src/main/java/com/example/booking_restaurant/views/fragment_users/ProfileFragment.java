package com.example.booking_restaurant.views.fragment_users;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.views.activities.AdminActivity;
import com.example.booking_restaurant.views.activities.MainActivity;
import com.example.booking_restaurant.views.activities.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    private Button btnSignOut;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstancesState){
        View view = inflater.inflate(R.layout.fragment_profile, container,false);
        mAuth = FirebaseAuth.getInstance();
        btnSignOut = view.findViewById(R.id.btnSignOut);


        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandleSignOut(view);
            }
        });

        return view;
    }

    private void HandleSignOut(View view){
        mAuth.signOut();
        startActivity(new Intent(getActivity(), SignInActivity.class));
        getActivity().finish();
    }
}