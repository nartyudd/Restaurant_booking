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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.booking_restaurant.R;
import com.example.booking_restaurant.utilities.GenID;
import com.example.booking_restaurant.views.Helpers.OnUpdateRestaurantListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class URestaurantFragment extends Fragment {


    private static final String ARG_PARAM1 = "uuid";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "address";
    private static final String ARG_PARAM4 = "uriImage";

    // TODO: Rename and change types of parameters
    private String uuid;
    private String name;
    private String address;
    private String uriImage;


    private static final String TAG = "UPDATE_RESTAURANT_FRAGMENT";

    public URestaurantFragment() {
        // Required empty public constructor
    }

    public static URestaurantFragment newInstance(String uuid, String name, String address, String uriImage) {
        URestaurantFragment fragment = new URestaurantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, uuid);
        args.putString(ARG_PARAM2, name);
        args.putString(ARG_PARAM3, address);
        args.putString(ARG_PARAM4, uriImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            uuid = getArguments().getString(ARG_PARAM1);
            name = getArguments().getString(ARG_PARAM2);
            address = getArguments().getString(ARG_PARAM3);
            uriImage = getArguments().getString(ARG_PARAM4);
        }
    }

    private EditText editUpName, editUpAddress;
    private Button btnUpRestaurant, uploadButtonImage;
    private LinearProgressIndicator progressIndicatorUp;
    private ImageView imageViewUpRestaurant;
    private Uri imageNewUri;
    private StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_u_restaurant, container, false);
        Init(view);
        return view;
    }

    private OnUpdateRestaurantListener updateRestaurantListener;

    public void setOnUpdateRestaurantListener(OnUpdateRestaurantListener listener) {
        this.updateRestaurantListener = listener;
    }

    public void Init(View view) {
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://comic-world-f4667.appspot.com");
        editUpName = view.findViewById(R.id.editUpName);
        editUpAddress = view.findViewById(R.id.editUpAddress);
        imageViewUpRestaurant = view.findViewById(R.id.imageViewUpRestaurant);
        btnUpRestaurant = view.findViewById(R.id.btnUpRestaurant);
        uploadButtonImage = view.findViewById(R.id.uploadButtonImage);
        editUpName.setText(name);
        editUpAddress.setText(address);

        Glide.with(view)
                .load(uriImage)
                .into(imageViewUpRestaurant);

        uploadButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeleteImage(uriImage);
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                activityResultLauncher.launch(intent);
            }
        });

        HandleBtnUpdate(btnUpRestaurant);
    }

    private void DeleteImage(String uriImage) {
        int indexBegin = "https://firebasestorage.googleapis.com/v0/b/comic-world-f4667.appspot.com/o/image_restaurants%2F".length();
        int indexEnd = uriImage.indexOf("?alt=media&token=bcb89d5f-5426-487f-9c15-f55c957b1ffe");


        String imagePath = "image_restaurants%2F25d9b3f3-6043-47ce-9281-7b184a3ac277"; // Đường dẫn của hình ảnh trong Cloud Storage
        StorageReference storageRef = storageReference.child(imagePath);

        Log.i(TAG, "" + uriImage.substring(indexBegin, indexEnd));
        storageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "delete success" + uriImage.substring(indexBegin, indexEnd));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e(TAG, "delete failed" + uriImage.substring(indexBegin, indexEnd));
            }
        });;
    }

    public void HandleBtnUpdate(Button btnUpRestaurant) {
        btnUpRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRestaurantListener.onUpdateRestaurantClick(uuid, name, address, uriImage);
            }
        });
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    imageViewUpRestaurant.setEnabled(true);
                    imageNewUri = result.getData().getData();
                    Glide.with(getActivity().getApplicationContext()).load(imageNewUri).into(imageViewUpRestaurant);
                }
            } else {
                Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
    });


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
//                        Restaurant res = new Restaurant(GenID.genUUID(), name, address, rating, imageUrl);
//                        resRepo.AddRestaurant(res);
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
                progressIndicatorUp.setMax(Math.toIntExact(taskSnapshot.getTotalByteCount()));
                progressIndicatorUp.setProgress(Math.toIntExact(taskSnapshot.getBytesTransferred()));
            }
        });
    }

}