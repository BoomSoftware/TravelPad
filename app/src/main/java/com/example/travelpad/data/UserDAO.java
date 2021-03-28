package com.example.travelpad.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.travelpad.models.User;
import com.example.travelpad.util.SeederConfig;
import java.util.List;

public class UserDAO {
    private static UserDAO instance;
    private List<User> users;

    private UserDAO() {
        users = SeederConfig.seedUsers();
    }

    public static UserDAO getInstance(){
        if(instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public boolean signIn(String email, String password){
        for(User user : users){
            if(user.getPassword().equals(password) && user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean signUp(String email, String password){
        users.add(new User(email, password));
        return true;
    }
}
