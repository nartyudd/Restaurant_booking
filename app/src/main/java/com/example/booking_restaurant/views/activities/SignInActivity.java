package com.example.booking_restaurant.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booking_restaurant.R;
import com.example.booking_restaurant.data.models.UserSingleton;
import com.example.booking_restaurant.data.repository_users.BaseRepository;
import com.example.booking_restaurant.data.repository_users.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignIn Activity";

    private EditText edt_email;
    private EditText edt_password;
    private Button btn_sign_in;
    private TextView btn_sign_up_form;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private UserRepository userRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edt_email = this.findViewById(R.id.edt_email);
        edt_password = this.findViewById(R.id.edt_password);
        btn_sign_in = this.findViewById(R.id.btn_sign_in);
        btn_sign_up_form = this.findViewById(R.id.btn_sign_up_form);
        userRepo = new UserRepository();

        mAuth = FirebaseAuth.getInstance();
        CheckUserLogin();
        btnSignIn(btn_sign_in);
        btnSignUp(btn_sign_up_form);

    }
    private void btnSignUp(TextView btn_sign_up_form){
        btn_sign_up_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void btnSignIn(Button btn_sign_in){
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    private void SignIn(){
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        userRepo.checkExistsAsync(email).addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if(task.isSuccessful()){

                    boolean exists = task.getResult();

                    if(exists){
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignInActivity.this, "Đăng nhập thành công.!",
                                                    Toast.LENGTH_SHORT).show();
                                            String uid = task.getResult().getUser().getUid().toString().trim();
                                            CheckUserRole(uid);
                                        } else {
                                            Toast.makeText(SignInActivity.this, "Tài khoản hoặc mật khẩu không đúng.!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(SignInActivity.this, "Taì khoản của bạn đã bị vô hiệu hóa.!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private void CheckUserLogin(){
        mUser = mAuth.getCurrentUser();
        if(mUser != null){
            CheckUserRole(mUser.getUid());
        }
    }

    private void CheckUserRole(String uid){
        userRepo.getRole(uid, new BaseRepository.OnDataFetchedListener<String>() {
            @Override
            public void onDataFetched(String role) {
                switch (role) {
                    case "user":
                        SaveCurrentUser(uid, role);
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        finish();
                        break;
                    case "admin":
                        SaveCurrentUser(uid, role);
                        startActivity(new Intent(SignInActivity.this, AdminActivity.class));
                        finish();
                        break;
                }
            }
        });

    }

    private void SaveCurrentUser(String uid, String role) {
        if(uid != null){
            UserSingleton.getInstance().setUserID(uid);
            UserSingleton.getInstance().setRole(role);
        }
        else{
            Log.e(TAG, "Fail save user id.!");
        }
    }


}