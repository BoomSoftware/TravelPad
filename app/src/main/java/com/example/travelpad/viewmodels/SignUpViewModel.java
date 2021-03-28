package com.example.travelpad.viewmodels;

import androidx.lifecycle.ViewModel;
import com.example.travelpad.repositories.UserRepository;

public class SignUpViewModel extends ViewModel {
    private UserRepository userRepository;

    public SignUpViewModel(){
        userRepository = UserRepository.getInstance();
    }

    public boolean signUp(String email, String password, String confirmPassword){
        if(validateSignUp(email, password, confirmPassword)){
            return userRepository.signUp(email, password);
        }
       return false;
    }

    private boolean validateSignUp(String email, String password, String confirmPassword){
        if(email.equals("") || password.equals("") || confirmPassword.equals("")){
            return false;
        }
        return password.equals(confirmPassword);
    }
}
