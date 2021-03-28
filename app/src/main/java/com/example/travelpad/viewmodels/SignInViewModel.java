package com.example.travelpad.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.travelpad.repositories.UserRepository;

public class SignInViewModel extends ViewModel {
    private UserRepository userRepository;

    public SignInViewModel(){
        userRepository = UserRepository.getInstance();
    }

    public boolean signIn(String email, String password){
        if(validateSignIn(email,password)){
            return userRepository.signIn(email, password);
        }
        return false;
    }

    private boolean validateSignIn(String email, String password){
        if(email.equals("") || password.equals("")){
            return false;
        }
        return true;
    }

}
