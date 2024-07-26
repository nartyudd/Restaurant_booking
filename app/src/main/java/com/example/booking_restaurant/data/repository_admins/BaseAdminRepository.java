package com.example.booking_restaurant.data.repository_admins;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public abstract class BaseAdminRepository {
    protected FirebaseFirestore db;
    protected CollectionReference coRef;
    public interface OnDataFetchedListener<T> {
        void onDataFetched(T data);
    }
}
