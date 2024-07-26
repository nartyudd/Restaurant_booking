package com.example.booking_restaurant.views.fragment_admins;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.Restaurant;
import com.example.booking_restaurant.data.repository_admins.RestaurantAdminRepository;
import com.example.booking_restaurant.utilities.GenID;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CRestaurantFragment extends Fragment {

    private static final String TAG = "CU_RESTAURANT_FRAGMENT";
    private StorageReference storageReference;
    private Button uploadImage;
    private Uri imageUri;
    private ImageView imageView;
    private LinearProgressIndicator progressIndicator;
    private EditText editName, editAddress;
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "address";
    private static final String ARG_PARAM3 = "rating";
    private static final String ARG_PARAM4 = "uriImage";

    private RestaurantAdminRepository resRepo;
    private Button btnAddRestaurant;
    private RatingBar ratingBarRestaurant;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    uploadImage.setEnabled(true);
                    imageUri = result.getData().getData();
                    Glide.with(getActivity().getApplicationContext()).load(imageUri).into(imageView);
                }
            } else {
                Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
    });


    public CRestaurantFragment() {

    }

    public static CRestaurantFragment newInstance() {
        CRestaurantFragment fragment = new CRestaurantFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_u_restaurant, container, false);

        Init(view);

        return view;
    }


    private void Init(View view) {
        uploadImage = view.findViewById(R.id.uploadButtonImage);
        imageView = view.findViewById(R.id.imageViewRestaurant);
        progressIndicator = view.findViewById(R.id.progress);
        btnAddRestaurant = view.findViewById(R.id.btnAddRestaurant);
        editName = view.findViewById(R.id.editName);
        editAddress = view.findViewById(R.id.editAddress);
        ratingBarRestaurant = view.findViewById(R.id.ratingBarRestaurant);
        storageReference = FirebaseStorage.getInstance().getReference();

        resRepo = new RestaurantAdminRepository();
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRestaurant();
            }
        });
    }

    private void AddRestaurant() {
        String name = editName.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        float rating = ratingBarRestaurant.getRating();

        if(ValidateAddRestaurant(imageUri, name, address))
            uploadImage(imageUri, name, address, rating);
        else{
            Toast.makeText(getActivity(), "Bạn phải điền đầy đủ các trường cần thiết.!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ValidateAddRestaurant(Uri file, String name, String address){
        if(name == null || address == null || file == null
                || name.equals("") || address.equals("") || file.equals("")){
            return false;
        }
        return true;
    }

    private void uploadImage(Uri file, String name, String address, float rating) {

        StorageReference ref = storageReference.child("image_restaurants/" + GenID.genUUID());

        UploadTask uploadTask = ref.putFile(file);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i(TAG, "Upload image restaurant success.");

                // Lấy đường dẫn URL của tệp đã tải lên
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        Restaurant res = new Restaurant(GenID.genUUID(), name, address, rating, imageUrl);
                        resRepo.AddRestaurant(res);
                        Toast.makeText(getActivity(), "Bạn đã thêm thành công.!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Failed to get image URL: " + e.getMessage());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Failed to upload image restaurant: " + e.getMessage());
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                progressIndicator.setMax(Math.toIntExact(taskSnapshot.getTotalByteCount()));
                progressIndicator.setProgress(Math.toIntExact(taskSnapshot.getBytesTransferred()));
            }
        });
    }
}