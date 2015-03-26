package com.dbd.demo.service;

import com.dbd.demo.domain.User;

public interface UserService {

    void createUser(User user);
    User findUser(String userId);
    void deleteUser(String userId);

}
