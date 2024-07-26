package com.example.booking_restaurant.data.repository_admins;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.booking_restaurant.viewmodels.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserAdminRepository extends BaseAdminRepository {
    private static final String TAG = "USER_ADMIN_REPOSITORY";

    public UserAdminRepository(){
        this.db = FirebaseFirestore.getInstance();
        this.coRef = db.collection("user");
    }

    public void getListUser(OnDataFetchedListener listener){
        Log.e(TAG, "getListUser.!!");

        coRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<UserViewModel> users = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                users.add(document.toObject(UserViewModel.class));
                            }

                            listener.onDataFetched(users);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });

    }

    public boolean ChangeRole(String userId, String newRole){
        AtomicBoolean isSuccess = new AtomicBoolean(true);

        Query query = coRef.whereEqualTo("userId", userId);

        query.get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();

                        if(querySnapshot.getDocuments() != null || !querySnapshot.isEmpty()) {

                            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);

                            if(documentSnapshot != null) {
                                String documentId = documentSnapshot.getId();

                                if(documentId != null) {

                                    coRef.document(documentId).update("role", newRole);
                                    isSuccess.set(true);
                                }
                            } else {
                                Log.i("ADMIN_USER_REPOSITORY", documentSnapshot.toString());
                                isSuccess.set(false);
                                Log.e("ADMIN_USER_REPOSITORY", "errrorr : " + documentSnapshot.toString());
                            }
                        } else {
                            Log.e("ADMIN_USER_REPOSITORY", "errrorr : " + querySnapshot.toString());
                        }
                    } else {
                        Log.e("ADMIN_USER_REPOSITORY", "errrorr : " + task.getException());
                    }
                });

        return isSuccess.get();
    }


    public boolean ChangeExists(String userId, boolean newExisst){
        // Change exists user in firestore
        AtomicBoolean isSuccess = new AtomicBoolean(true);

        Query query = coRef.whereEqualTo("userId", userId);

        query.get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();

                        if(querySnapshot.getDocuments() != null || !querySnapshot.isEmpty()) {

                            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);

                            if(documentSnapshot != null) {
                                String documentId = documentSnapshot.getId();

                                if(documentId != null) {

                                    coRef.document(documentId).update("exists", newExisst);
                                    isSuccess.set(true);
                                }
                            } else {
                                Log.i("ADMIN_USER_REPOSITORY", documentSnapshot.toString());
                                isSuccess.set(false);
                                Log.e("ADMIN_USER_REPOSITORY", "error : " + documentSnapshot.toString());
                            }
                        } else {
                            Log.e("ADMIN_USER_REPOSITORY", "error : " + querySnapshot.toString());
                        }
                    } else {
                        Log.e("ADMIN_USER_REPOSITORY", "error : " + task.getException());
                    }
                });

        return isSuccess.get();
    }

//    public boolean DeleteUser(String userId, boolean isExists){
//        AtomicBoolean isSuccess = new AtomicBoolean(true);
//        coRef.document(userId).update("isExists", !isExists).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                isSuccess.set(true);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                isSuccess.set(false);
//                Log.e(TAG, e.toString());
//            }
//        });
//
//        return isSuccess.get();
//    }

}
