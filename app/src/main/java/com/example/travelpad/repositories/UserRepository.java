package com.example.travelpad.repositories;

import androidx.lifecycle.LiveData;

import com.example.travelpad.data.UserDAO;
import com.example.travelpad.models.User;

public class UserRepository {
    private UserDAO userDAO;
    private static UserRepository instance;

    private UserRepository(){
        this.userDAO = UserDAO.getInstance();
    }

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public boolean signIn(String email, String password){
        return userDAO.signIn(email, password);
    }

    public boolean signUp(String email, String password){
        return userDAO.signUp(email,password);
    }
}
