package com.example.booking_restaurant.data.repository_admins;

import android.util.Log;

import com.example.booking_restaurant.data.models.Category;
import com.example.booking_restaurant.data.models.Region;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdminRepository extends BaseAdminRepository{
    private static final String TAG = "CATEGORY_ADMIN_REPOSITORY";

    public CategoryAdminRepository(){
        this.db = FirebaseFirestore.getInstance();
        this.coRef = db.collection("category");
    }

    public void AddCategory(Category category){
        coRef.add(category);
    }

    public void GetCategoy(String idRestaurant, OnDataFetchedListener listener){
        Query query = coRef.whereEqualTo("idRestaurant", idRestaurant);

        query.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                List<Category> _cates = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    _cates.add(document.toObject(Category.class));
                    Log.i(TAG, document.toObject(Category.class).toString());
                }

                listener.onDataFetched(_cates);
            } else {
                Log.e(TAG, "get list restaurant failed");
            }
        });
    }

    public void UpdateCate(String uuid, String name){
        Query query = coRef.whereEqualTo("uuid", uuid);

        query.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                if(documentSnapshot != null){
                    String id = documentSnapshot.getId();

                    coRef.document(id).update("name", name);
                }
            }
        });
    }

    public void DeleteCate(String uuid){
        Query query = coRef.whereEqualTo("uuid", uuid);

        query.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                if(documentSnapshot != null){
                    String id = documentSnapshot.getId();

                    coRef.document(id).delete();
                }
            }
        });
    }
}
