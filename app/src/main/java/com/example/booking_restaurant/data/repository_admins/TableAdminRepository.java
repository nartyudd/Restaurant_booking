package com.example.booking_restaurant.data.repository_admins;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.booking_restaurant.data.models.Region;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.models.Table;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TableAdminRepository extends BaseAdminRepository{
    private static final String TAG = "TABLE_ADMIN_REPOSITORY";

    public TableAdminRepository(){
        this.db = FirebaseFirestore.getInstance();
        this.coRef = db.collection("table");
    }

    public void AddTable(Table table){
        coRef.add(table);
    }

    public void GetAllTable(String regionId, OnDataFetchedListener listener){
        Query query = coRef.whereEqualTo("regionId", regionId);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<Table> _tables = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        _tables.add(document.toObject(Table.class));
                    }

                    listener.onDataFetched(_tables);
                } else {
                    Log.e(TAG, "get list restaurant failed");
                }
            }
        });
    }
}
