package com.example.booking_restaurant.data.repository_users;

import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.booking_restaurant.data.models.User;
import com.example.booking_restaurant.viewmodels.UserViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.atomic.AtomicBoolean;


public class UserRepository extends BaseRepository {
    private static final String TAG = "UserRepository";

    public UserRepository() {
        this.db = FirebaseFirestore.getInstance();
        this.coRef = db.collection("user");
    }


    public void AddUser(UserViewModel user){
        coRef.add(user).addOnCompleteListener(task -> {
           if(task.isSuccessful()) {
               Log.i(TAG, task.getResult().getId().toString());
           } else {
               Log.e(TAG, task.getResult().toString());

           }
        });
    }

    public Task<Boolean> checkExistsAsync(String email) {
        Query query = coRef.whereEqualTo("email", email);

        return query.get().continueWith(new Continuation<QuerySnapshot, Boolean>() {
            @Override
            public Boolean then(@NonNull Task<QuerySnapshot> task) throws Exception {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                        return documentSnapshot.toObject(UserViewModel.class).isExists();
                    } else {
                        return false;
                    }
                } else {
                    throw task.getException();
                }
            }
        });
    }


    public void getRole(String userId, OnDataFetchedListener listener){
        Query query = coRef.whereEqualTo("userId", userId);
        query.get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {

                        QuerySnapshot querySnapshot = task.getResult();
                        if(querySnapshot != null && !querySnapshot.isEmpty()) {

                            String userRole;

                            userRole = querySnapshot.getDocuments().get(0).getString("role");

                            if(userRole != null) {
                                listener.onDataFetched(userRole);
                            } else {
                                listener.onDataFetched(null);
                            }

                        }

                    }
                });
    }
}
