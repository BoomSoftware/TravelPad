package com.example.travelpad.util;

import com.example.travelpad.models.User;

import java.util.ArrayList;
import java.util.List;

public class SeederConfig {

    public static List<User> seedUsers(){
        List<User> users = new ArrayList<>();

        User user1 = new User("test@test.com", "test");
        user1.setId(1);
        User user2 = new User("bob@test.com", "bob1234");
        user1.setId(2);

        users.add(user1);
        users.add(user2);

        return users;
    }

}
