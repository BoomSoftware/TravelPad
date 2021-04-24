package com.example.travelpad.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.travelpad.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivityViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
}
