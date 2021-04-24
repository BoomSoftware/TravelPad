package com.example.travelpad.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.travelpad.models.UserLiveData;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    private final UserLiveData currentUser;
    private final Application application;
    private static UserRepository instance;

    private UserRepository(Application application){
        this.currentUser = new UserLiveData();
        this.application = application;
    }

    public static UserRepository getInstance(Application application) {
        if (instance == null) {
            instance = new UserRepository(application);
        }
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() { return currentUser;}

    public void signOut(){
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }

}
