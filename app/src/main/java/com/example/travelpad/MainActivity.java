package com.example.travelpad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.travelpad.viewmodels.LoginActivityViewModel;
import com.firebase.ui.auth.AuthUI;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int RC_SIGN_IN = 30;
    private LoginActivityViewModel loginActivityViewModel;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        prepareUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            handleSignInRequest(resultCode);
        }
    }

    private void prepareUI(){
        loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(v-> {
            signIn();
        });
    }

    private void handleSignInRequest(int resultCode){
        if(resultCode == RESULT_OK) {
            openHomeAppActivity();
        }
    }

    private void signIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void checkIfSignedIn(){
        loginActivityViewModel.getCurrentUser().observe(this, user -> {
            if(user != null){
                openHomeAppActivity();
            }
        });
    }

    private void openHomeAppActivity(){
        Intent intent  = new Intent(this, HomeAppActivity.class);
        startActivity(intent);
    }
}