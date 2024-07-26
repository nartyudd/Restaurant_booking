package com.example.booking_restaurant.data.repository_admins;

import android.util.Log;

import com.example.booking_restaurant.data.models.Region;
import com.example.booking_restaurant.data.models.Restaurant;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionAdminRepository extends BaseAdminRepository{
    private static final String TAG = "REGION_ADMIN_REPOSITORY";

    public RegionAdminRepository(){
        this.db = FirebaseFirestore.getInstance();
        this.coRef = db.collection("region");
    }

    public void AddRegion(Region region){
        coRef.add(region);
    }

    public void GetRegions(String idRestaurant, OnDataFetchedListener listener){
        Query query = coRef.whereEqualTo("idRestaurant", idRestaurant);

        query.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                List<Region> _region = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    _region.add(document.toObject(Region.class));
                    Log.i(TAG, document.toObject(Region.class).toString());
                }

                listener.onDataFetched(_region);
            } else {
                Log.e(TAG, "get list restaurant failed");
            }
        });
    }

    public void UpdateRegion(String uuid, String name){
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

    public void DeleteRegion(String uuid){
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
