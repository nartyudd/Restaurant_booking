package com.example.booking_restaurant.data.repository_users;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public abstract class BaseRepository {
    protected FirebaseFirestore db;
    protected CollectionReference coRef;
    public interface OnDataFetchedListener<T> {
        void onDataFetched(T data);
    }
}
