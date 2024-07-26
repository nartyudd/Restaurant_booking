package com.example.booking_restaurant.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.booking_restaurant.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        btn = findViewById(R.id.btn_sign_in_wel);

        };
    public void signInWel(View view) {
        startActivities(new Intent[]{new Intent(WelcomeActivity.this, SignInActivity.class)});
    }
    public void signUpWel(View view) {
        startActivities(new Intent[]{new Intent(WelcomeActivity.this, SignUpActivity.class)});
    }

}
